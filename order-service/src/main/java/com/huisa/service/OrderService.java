package com.huisa.service;

import com.huisa.dto.OrderRequest;
import com.huisa.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse createOrder(OrderRequest orderRequest);

    OrderResponse updateOrder(Long id, OrderRequest orderRequest);

    void deleteOrder(Long id);





}
