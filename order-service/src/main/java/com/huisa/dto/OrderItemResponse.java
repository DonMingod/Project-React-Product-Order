package com.huisa.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemResponse(

        Long productId,

        String productName,

        BigDecimal price,

        Integer quantity,

        BigDecimal subtotal
) {
}
