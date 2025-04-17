package com.ecommerce.orderservice.domain.repository;

import com.ecommerce.orderservice.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
