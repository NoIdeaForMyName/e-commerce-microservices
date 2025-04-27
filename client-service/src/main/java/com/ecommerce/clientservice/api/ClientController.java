package com.ecommerce.clientservice.api;


import com.ecommerce.clientservice.domain.DTO.ClientDTO;
import com.ecommerce.clientservice.domain.DTO.CreateReservationDTO;
import com.ecommerce.clientservice.domain.entity.ClientEntity;
import com.ecommerce.clientservice.infrastructure.service.ClientCommandService;
import com.ecommerce.clientservice.infrastructure.service.ReservationCommandService;
import com.ecommerce.common.DTO.CreditCardDTO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientCommandService clientCommandService;
    private final ReservationCommandService reservationCommandService;

    public ClientController(ClientCommandService clientCommandService, ReservationCommandService reservationCommandService) {
        this.clientCommandService = clientCommandService;
        this.reservationCommandService = reservationCommandService;
    }

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody ClientDTO clientDTO) {
        System.out.println("create client:");
        System.out.println(clientDTO.getFirstname() + "\n" + clientDTO.getLastname() + "\n" + clientDTO.getEmail());

        ClientEntity client = clientCommandService.createClient(clientDTO);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/credit-card")
    public ResponseEntity<Object> assignCreditCard(@PathVariable long id, @RequestBody CreditCardDTO creditCardDTO) {
        try {
            clientCommandService.assignCreditCard(id, creditCardDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ObjectNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/reservation")
    public ResponseEntity<Object> addReservation(@PathVariable long id, @RequestBody CreateReservationDTO createReservationDTO) {
        reservationCommandService.createReservation(id, createReservationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
