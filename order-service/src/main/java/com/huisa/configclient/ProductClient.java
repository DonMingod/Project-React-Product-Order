package com.huisa.configclient;

import com.huisa.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestTemplate restTemplate;

    private static final String PRODUCT_SERVICE_URL =
            "http://localhost:8082/products/{id}"; // SIN ESPACIO

    public ProductDto getProductById(Long productId) {
        return restTemplate.getForObject(
                PRODUCT_SERVICE_URL,
                ProductDto.class,
                productId
        );
    }
}
