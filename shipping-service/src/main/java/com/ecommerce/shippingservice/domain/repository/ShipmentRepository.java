package com.ecommerce.shippingservice.domain.repository;

import com.ecommerce.shippingservice.domain.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {
}
