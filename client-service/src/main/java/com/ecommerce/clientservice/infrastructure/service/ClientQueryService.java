package com.ecommerce.clientservice.infrastructure.service;

import com.ecommerce.clientservice.domain.entity.ClientEntity;
import com.ecommerce.clientservice.domain.repository.ClientRepository;
import com.ecommerce.clientservice.domain.repository.CreditCardRepository;
import org.springframework.stereotype.Service;


@Service
public class ClientQueryService {

    private final ClientRepository clientRepository;

    public ClientQueryService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientEntity getClient(long id) {
        return clientRepository.findById(id).orElse(null);
    }

}
