package com.ecommerce.clientservice.domain.repository;

import com.ecommerce.clientservice.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
