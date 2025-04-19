package com.ecommerce.paymentservice.infrastructure.service;

import com.ecommerce.paymentservice.domain.DTO.PaymentDTO;
import com.ecommerce.paymentservice.domain.entity.PaymentEntity;
import com.ecommerce.paymentservice.domain.repository.PaymentRepository;
import org.springframework.stereotype.Service;


@Service
public class PaymentCommandService {

    private final PaymentRepository paymentRepository;

    public PaymentCommandService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentEntity createPayment(PaymentDTO paymentDTO) {
        PaymentEntity newPayment = new PaymentEntity(paymentDTO.getClientId(), paymentDTO.getOrderId(), paymentDTO.getAmount());
        paymentRepository.save(newPayment);
        return newPayment;
    }

}
