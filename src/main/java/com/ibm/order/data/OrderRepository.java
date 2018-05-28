package com.ibm.order.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.order.model.OrderRequest;

public interface OrderRepository extends JpaRepository<OrderRequest, Integer> {

}
