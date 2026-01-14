package com.huisa.service.impl;

import com.huisa.configclient.ProductClient;
import com.huisa.dto.OrderRequest;
import com.huisa.dto.OrderResponse;
import com.huisa.model.Order;
import com.huisa.model.OrderItem;
import com.huisa.repository.OrderRepository;
import com.huisa.service.OrderService;
import com.huisa.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private  final ProductClient productClient;


    @Override
    public List<OrderResponse> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order not found with id: " + id)
        ));
    }

   //@Override
   // public OrderResponse createOrder(OrderRequest orderRequest) {
     //   Order order = orderRepository.save(orderMapper.toEntity(orderRequest));
       // return orderMapper.toDto(order);
    //}

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        Order order = orderMapper.toEntity(orderRequest);
        order.setCreatedAt(LocalDate.now());

        order.getItems().forEach(item -> {

            var product = productClient.getProductById(item.getProductId());

            item.setProductName(product.name());
            item.setPrice(product.price());

            // MUY IMPORTANTE
            item.setOrder(order);
        });

        BigDecimal total = order.getItems().stream()
                .map(item ->
                        item.getPrice()
                                .multiply(BigDecimal.valueOf(item.getQuantity()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Order not found with id: " + id
                ));
        order.setCustomerName(orderRequest.customerName());
        order.getItems().clear();
        orderRequest.items().forEach(itemRequest -> {

            var product = productClient.getProductById(itemRequest.productId());

            OrderItem item = OrderItem.builder()
                    .productId(product.id())
                    .productName(product.name())
                    .price(product.price())
                    .quantity(itemRequest.quantity())
                    .order(order)
                    .build();

            order.getItems().add(item);
        });
        BigDecimal total = order.getItems().stream()
                .map(i -> i.getPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);

        return orderMapper.toDto(orderRepository.save(order));
    }


   // @Override
    //public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
      //  Order orderFound = orderRepository.findById(id).orElseThrow(
        //        () -> new RuntimeException("Order not found with id: " + id)
       // );
       // Order updatedOrder = orderRepository.save(orderFound);
       // return orderMapper.toDto(updatedOrder);
   // } -->

    @Override
    public void deleteOrder(Long id) {
        Order orderFound = orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Order not found with id: " + id)
        );
        orderRepository.delete(orderFound);
    }
}

