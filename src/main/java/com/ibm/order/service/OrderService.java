package com.ibm.order.service;

import com.ibm.order.model.DeliveryAddress;
import com.ibm.order.model.OrderRequest;
import com.ibm.order.model.OrderResponse;

public interface OrderService {
	public boolean authorizeOrderRequest(OrderRequest orderRequest) throws OrderValidationException;
	public boolean validateDeliveryAddress(DeliveryAddress orderRequest) throws OrderValidationException;
	public OrderResponse processOrderItems(OrderRequest orderRequest);
}
