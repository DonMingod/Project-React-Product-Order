package com.huisa.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderItemRequest(

        @NotNull
        Long orderId,

        @NotNull
        Long productId,

        @NotNull
        Integer quantity
) {}
