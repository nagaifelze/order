package com.ibm.order.rest;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.order.data.OrderRepository;
import com.ibm.order.model.OrderRequest;
import com.ibm.order.model.OrderResponse;
import com.ibm.order.service.OrderService;
import com.ibm.order.service.OrderValidationException;
import com.ibm.order.service.PartManager;

@RestController
public class OrderResource {
	
	@Autowired
	OrderRepository repoitory;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	PartManager partManager;
	
	@PostMapping(path="/order", consumes= {"application/xml"}, produces= {"application/xml"})
	public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest order) throws OrderValidationException {
		orderService.authorizeOrderRequest(order);
		
		orderService.validateDeliveryAddress(order.getDeliveryAddress());
		
		OrderResponse response = orderService.processOrderItems(order);
		
		OrderRequest savedOrder = repoitory.save(order);
		
		return ResponseEntity.created(URI.create("/order/" + savedOrder.getOrderId())).body(response);
	}
}
