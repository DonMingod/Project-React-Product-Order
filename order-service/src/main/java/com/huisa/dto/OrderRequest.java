package com.huisa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import java.util.List;

@Builder
public record OrderRequest(

        @NotBlank
        String customerName,

        @NotEmpty
        List<OrderItemRequest> items
) {}
