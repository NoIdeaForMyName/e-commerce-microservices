package com.ecommerce.clientservice.infrastructure.service;

import com.ecommerce.clientservice.domain.DTO.BasketDTO;
import com.ecommerce.clientservice.domain.DTO.BasketDetailDTO;
import com.ecommerce.clientservice.domain.entity.ReservationEntity;
import com.ecommerce.clientservice.domain.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservationQueryService {

    private final ReservationRepository reservationRepository;

    public ReservationQueryService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public BasketDTO getClientBasket(Long clientId) {
        List<ReservationEntity> reservations = reservationRepository.findById_FkClient(clientId);
        BasketDTO basket = new BasketDTO(clientId);

        for (ReservationEntity reservation : reservations)
            basket.getDetails().add(new BasketDetailDTO(reservation.getId().getFkProduct(), reservation.getQuantity()));

        return basket;
    }

}
