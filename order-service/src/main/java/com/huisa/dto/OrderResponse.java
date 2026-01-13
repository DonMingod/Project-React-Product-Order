package com.huisa.dto;

import com.huisa.model.OrderItem;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record OrderResponse(

        Long id,
        String customerName,
        BigDecimal total,
        LocalDate createdAt,
        List<OrderItemResponse> items
) {
}
