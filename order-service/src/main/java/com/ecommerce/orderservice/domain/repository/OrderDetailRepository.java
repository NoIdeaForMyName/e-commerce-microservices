package com.ecommerce.orderservice.domain.repository;

import com.ecommerce.orderservice.domain.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
}
