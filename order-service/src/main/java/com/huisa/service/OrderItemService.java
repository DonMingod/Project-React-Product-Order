package com.huisa.service;

import com.huisa.dto.OrderItemRequest;
import com.huisa.dto.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponse> getAllOrderItems();

    OrderItemResponse getOrderItemById(Long id);

    OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest);

    OrderItemResponse updateOrderItem(Long id, OrderItemRequest orderItemRequest);

    void deleteOrderItem(Long id);

}
