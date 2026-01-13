package com.huisa.util;

import com.huisa.dto.OrderItemRequest;
import com.huisa.dto.OrderItemResponse;
import com.huisa.model.OrderItem;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {OrderMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapper {

    // MapStruct mapeará por nombre productId, productName, price, quantity.
    // Ignoramos 'order' porque se setea en OrderMapper (@AfterMapping)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemRequest orderItemRequest);

    // Calculamos subtotal = price * quantity para el DTO
    @Mapping(target = "subtotal", expression = "java(orderItem.getPrice() != null && orderItem.getQuantity() != null ? orderItem.getPrice().multiply(java.math.BigDecimal.valueOf(orderItem.getQuantity())) : null)")
    OrderItemResponse toDto(OrderItem orderItem);

    List<OrderItemResponse> toDtoList(List<OrderItem> orderItems);
}
