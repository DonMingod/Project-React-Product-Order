package com.huisa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private BigDecimal total;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL)
    private List<OrderItem> items;

    // Getters and Setters

}
