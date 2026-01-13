package com.huisa.repository;

import com.huisa.model.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<@NonNull OrderItem , @NonNull Long> {
}
