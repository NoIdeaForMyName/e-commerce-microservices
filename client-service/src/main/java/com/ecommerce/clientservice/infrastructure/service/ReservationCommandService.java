package com.ecommerce.clientservice.infrastructure.service;

import com.ecommerce.clientservice.domain.DTO.CreateReservationDTO;
import com.ecommerce.clientservice.domain.entity.ClientEntity;
import com.ecommerce.clientservice.domain.entity.ReservationEntity;
import com.ecommerce.clientservice.domain.repository.ClientRepository;
import com.ecommerce.clientservice.domain.repository.ReservationRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;

    public ReservationCommandService(ReservationRepository reservationRepository, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }

    public void createReservation(long clientId, CreateReservationDTO createReservationDTO) {
        ClientEntity client = clientRepository.findById(clientId).orElseThrow();
        ReservationEntity newReservation = new ReservationEntity(client, createReservationDTO.getProductFk(), createReservationDTO.getQuantity());
        reservationRepository.save(newReservation);
    }

}
