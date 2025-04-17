package com.ecommerce.orderservice.api;

import com.ecommerce.orderservice.domain.DTO.PlaceOrderDTO;
import com.ecommerce.orderservice.infrastructure.service.OrderCommandService;
import com.ecommerce.orderservice.infrastructure.service.OrderQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    public OrderController(OrderCommandService orderCommandService, OrderQueryService orderQueryService) {
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping("/place")
    public ResponseEntity<Object> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        orderCommandService.placeOrder(placeOrderDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
