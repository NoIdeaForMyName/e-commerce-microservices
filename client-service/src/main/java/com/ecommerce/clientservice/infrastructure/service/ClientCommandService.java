package com.ecommerce.clientservice.infrastructure.service;

import com.ecommerce.clientservice.domain.DTO.ClientDTO;
import com.ecommerce.clientservice.domain.entity.ClientEntity;
import com.ecommerce.clientservice.domain.entity.CreditCardEntity;
import com.ecommerce.clientservice.domain.repository.ClientRepository;
import com.ecommerce.clientservice.domain.repository.CreditCardRepository;
import com.ecommerce.common.DTO.CreditCardDTO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ClientCommandService {

    private final ClientRepository clientRepository;
    private final CreditCardRepository creditCardRepository;

    public ClientCommandService(ClientRepository clientRepository, CreditCardRepository creditCardRepository) {
        this.clientRepository = clientRepository;
        this.creditCardRepository = creditCardRepository;
    }

    public void createClient(ClientDTO clientDTO) {
        ClientEntity newClient = new ClientEntity(
                clientDTO.getFirstname(),
                clientDTO.getLastname(),
                clientDTO.getEmail(),
                clientDTO.getAddress());
        clientRepository.save(newClient);
    }

    @Transactional
    public void assignCreditCard(long clientId, CreditCardDTO creditCardDTO) {
        CreditCardEntity newCreditCard = new CreditCardEntity(
                creditCardDTO.getNumber(),
                creditCardDTO.getCVV(),
                creditCardDTO.getExpirationDate());
        creditCardRepository.save(newCreditCard);
        Optional<ClientEntity> client = clientRepository.findById(clientId);
        if (client.isEmpty())
            throw new ObjectNotFoundException(clientId, "ClientEntity");
        client.ifPresent(clientEntity -> clientEntity.setCreditCard(newCreditCard));
    }

}
