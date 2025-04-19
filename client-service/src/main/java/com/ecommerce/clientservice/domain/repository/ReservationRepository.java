package com.ecommerce.clientservice.domain.repository;

import com.ecommerce.clientservice.domain.entity.ReservationEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findById_FkClient(@NotNull Long fkClient);
}
