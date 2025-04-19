package com.ecommerce.clientservice.domain.repository;

import com.ecommerce.clientservice.domain.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {
}
