package com.huisa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String productName;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private  Order order;



}
