package com.huisa.controller;


import com.huisa.dto.OrderItemRequest;
import com.huisa.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid OrderItemRequest orderItemRequest) {
        return ResponseEntity.ok(orderItemService.createOrderItem(orderItemRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid OrderItemRequest orderItemRequest) {
        return ResponseEntity.ok(orderItemService.updateOrderItem (id, orderItemRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
