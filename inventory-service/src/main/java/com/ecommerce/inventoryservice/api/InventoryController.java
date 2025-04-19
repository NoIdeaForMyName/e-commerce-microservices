package com.ecommerce.inventoryservice.api;

import com.ecommerce.inventoryservice.domain.DTO.ProductDTO;
import com.ecommerce.inventoryservice.infrastructure.service.InventoryCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryCommandService inventoryCommandService;

    public InventoryController(InventoryCommandService inventoryCommandService) {
        this.inventoryCommandService = inventoryCommandService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductDTO productDTO) {
        inventoryCommandService.createProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
