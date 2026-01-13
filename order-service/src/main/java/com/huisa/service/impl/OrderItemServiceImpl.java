package com.huisa.service.impl;

import com.huisa.dto.OrderItemRequest;
import com.huisa.dto.OrderItemResponse;
import com.huisa.model.Order;
import com.huisa.model.OrderItem;
import com.huisa.repository.OrderItemRepository;
import com.huisa.repository.OrderRepository;
import com.huisa.service.OrderItemService;
import com.huisa.util.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;


    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemMapper.toDtoList(orderItemRepository.findAll());
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        return orderItemMapper.toDto(orderItemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order Item not found with id: " + id)));
    }


  //  @Override
    //public OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest) {

      //  OrderItem orderItem = orderItemMapper.toEntity(orderItemRequest);

       // orderItem.setOrder(
              //  Order.builder().id(orderItemRequest.productId()).build()
        //);
        //return orderItemMapper.toDto(
          //      orderItemRepository.save(orderItem)
        //);
    //}
       @Override
       public OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest) {

           Order order = orderRepository.findById(orderItemRequest.orderId())
                   .orElseThrow(() -> new RuntimeException(
                           "Order not found with id: " + orderItemRequest.orderId()
                   ));

           OrderItem orderItem = orderItemMapper.toEntity(orderItemRequest);

           orderItem.setOrder(order);

           OrderItem saved = orderItemRepository.save(orderItem);

           return orderItemMapper.toDto(saved);
       }


    @Override
    public OrderItemResponse updateOrderItem(Long id, OrderItemRequest orderItemRequest) {
        OrderItem orderItemFound = orderItemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order Item not found with id: " + id)
        );

        orderItemFound.setProductId(orderItemRequest.productId());
        orderItemFound.setQuantity(orderItemRequest.quantity());

        return orderItemMapper.toDto(orderItemRepository.save(orderItemFound));
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItemFound = orderItemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order Item not found with id: " + id));
        orderItemRepository.delete(orderItemFound);
    }
}
