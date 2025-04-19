package com.ecommerce.clientservice;

import com.ecommerce.clientservice.domain.entity.ClientEntity;
import com.ecommerce.clientservice.domain.entity.CreditCardEntity;
import com.ecommerce.clientservice.domain.entity.ReservationEntity;
import com.ecommerce.clientservice.domain.repository.CreditCardRepository;
import com.ecommerce.clientservice.domain.repository.ReservationRepository;
import org.springframework.stereotype.Component;
import com.ecommerce.clientservice.domain.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Component
public class DatabaseFiller implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final CreditCardRepository creditCardRepository;
    private final ReservationRepository reservationRepository;

    public DatabaseFiller(ClientRepository clientRepository, CreditCardRepository creditCardRepository, ReservationRepository reservationRepository) {
        this.clientRepository = clientRepository;
        this.creditCardRepository = creditCardRepository;
        this.reservationRepository = reservationRepository;
    }

    public void fillDatabase() {
        CreditCardEntity newCreditCard = new CreditCardEntity(
                "1234567889",
                "123",
                new Date(2026, Calendar.MARCH, 11)
        );
        creditCardRepository.save(newCreditCard);

        ClientEntity newClient = new ClientEntity(
                "Michal",
                "Abak",
                "m.a@example.com",
                "Abakowska 22, 55-223 Włochowice"
        );
        newClient.setCreditCard(newCreditCard);
        clientRepository.save(newClient);

        ReservationEntity newReservation = new ReservationEntity(
                newClient,
                1L,
                3
        );
        reservationRepository.save(newReservation);
    }

    @Override
    @Transactional
    public void run(String... args) {
        fillDatabase();

//        System.out.println("TEST:");
//        ClientEntity client = clientRepository.findById(1L).orElse(null);
//        System.out.println("CLIENT: " + client);
//        assert client != null;
//        System.out.println("CREDIT CARD: " + client.getCreditCard());
    }
}
