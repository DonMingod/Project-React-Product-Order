package com.huisa.util;


import com.huisa.dto.OrderRequest;
import com.huisa.dto.OrderResponse;
import com.huisa.model.Order;
import com.huisa.model.OrderItem;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    // Mapea los items de OrderRequest a Order.items
    Order toEntity(OrderRequest orderRequest);

    OrderResponse toDto(Order order);

    List<OrderResponse> toDtoList(List<Order> orders);

    @AfterMapping
    default void linkItems(@MappingTarget Order order) {
        if (order != null && order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                item.setOrder(order);
            }
        }
        // Si no hay total, calcularlo a partir de los items (price * quantity)
        if (order != null && order.getTotal() == null) {
            BigDecimal total = BigDecimal.ZERO;
            if (order.getItems() != null) {
                for (OrderItem i : order.getItems()) {
                    if (i.getPrice() != null && i.getQuantity() != null) {
                        total = total.add(i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())));
                    }
                }
            }
            order.setTotal(total);
        }
    }
}
