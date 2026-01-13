package com.huisa.controller;

import com.huisa.model.Product;
import com.huisa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
