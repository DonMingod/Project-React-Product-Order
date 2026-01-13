package com.huisa.repository;

import com.huisa.model.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<@NonNull Product, @NonNull Long> {
}
