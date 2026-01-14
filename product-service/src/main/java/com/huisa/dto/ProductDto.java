package com.huisa.dto;

import lombok.Builder;

@Builder
public record ProductDto(
    Long id,
    String name,
    Double price
) {
}
