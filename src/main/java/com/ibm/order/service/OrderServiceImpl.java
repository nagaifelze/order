package com.ibm.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.order.model.DeliveryAddress;
import com.ibm.order.model.ErrorInfo;
import com.ibm.order.model.Item;
import com.ibm.order.model.ItemResult;
import com.ibm.order.model.OrderRequest;
import com.ibm.order.model.OrderResponse;
import com.ibm.order.service.PartManager.PartResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	Security security;
	
	@Autowired
	PartManager partManager;
	
	@Override
	public boolean authorizeOrderRequest(OrderRequest orderRequest) throws OrderValidationException {
		if(!security.IsDealerAuthorized(orderRequest.getDealer().getDealerId(), orderRequest.getDealer().getDealerAccessKey())) {
			throw new OrderValidationException(ErrorInfo.Not_Authorized);
		}
		return true;
	}

	@Override
	public boolean validateDeliveryAddress(DeliveryAddress deliveryaddress) throws OrderValidationException {
		if(deliveryaddress != null && !"B2T1A4".equalsIgnoreCase(deliveryaddress.getPostalcode())) {
			throw  new OrderValidationException(ErrorInfo.Invalid_Order_Delivery_Address);
		}
		return true;
	}

	@Override
	public OrderResponse processOrderItems(OrderRequest orderRequest) {
		List<Item> orderItems = orderRequest.getOrderItems();
		OrderResponse response = new OrderResponse();
		if(orderItems != null && !orderItems.isEmpty()) {
			List<ItemResult> resultItems = new ArrayList<>();
			orderItems.stream().forEach(item -> {
				ItemResult itemResult = new ItemResult(item);
				int partNum = item.getPartNumber();
				itemResult.setResult("success");
				if(!partManager.isValidPart(partNum)) {
					itemResult.setResult("failure");
					itemResult.setErrorMessage("invalid part");
				}else {
					PartResponse partResponse = partManager.submitPartForManufactureAndDelivery(partNum, item.getQuantity(), orderRequest.getDeliveryAddress());
					if(partResponse != null) {
						itemResult.setResult("failure");
						if(PartResponse.NO_LONGER_MANUFACTURED.equals(partResponse))
							itemResult.setErrorMessage("no longer manufactured");
						else if(PartResponse.OUT_OF_STOCK.equals(partResponse))
							itemResult.setErrorMessage("out of stock");
					}
				}
				resultItems.add(itemResult);
				response.setOrderItems(resultItems);
			});
		}
		return response;
	}

}
