package com.ecommerce.shippingservice.infrastructure.service;

import com.ecommerce.shippingservice.domain.DTO.ShipmentDTO;
import com.ecommerce.shippingservice.domain.entity.ShipmentEntity;
import com.ecommerce.shippingservice.domain.repository.ShipmentRepository;
import org.springframework.stereotype.Service;


@Service
public class ShipmentCommandService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentCommandService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public void createShipment(ShipmentDTO shipmentDTO) {
        ShipmentEntity newShipment = new ShipmentEntity(shipmentDTO.getFkOrder(), shipmentDTO.getAddress());
        shipmentRepository.save(newShipment);
    }

}
