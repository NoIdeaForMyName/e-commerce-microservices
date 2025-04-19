package com.ecommerce.inventoryservice.infrastructure.service;


import com.ecommerce.common.DTO.BasketDetailDTO;
import com.ecommerce.inventoryservice.domain.DTO.ProductDTO;
import com.ecommerce.inventoryservice.domain.entity.ProductEntity;
import com.ecommerce.inventoryservice.domain.repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InventoryCommandService {

    private final ProductRepository productRepository;

    public InventoryCommandService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductDTO productDTO) {
        ProductEntity newProduct = new ProductEntity(productDTO.getName(), productDTO.getAmount(), productDTO.getQuantity());
        productRepository.save(newProduct);
    }

    public void updateInventory(List<BasketDetailDTO> basketDetails) {
        for (BasketDetailDTO detail : basketDetails) {
            ProductEntity product = productRepository.findById(detail.getProductId()).orElseThrow();
            product.setQuantity(product.getQuantity() - detail.getQuantity());
            productRepository.save(product);
        }
    }

    public BigDecimal getTotalAmount(List<BasketDetailDTO> basketDetails) {
        BigDecimal total = new BigDecimal(0);
        for (BasketDetailDTO detail : basketDetails) {
            ProductEntity product = productRepository.findById(detail.getProductId()).orElseThrow();
            total = total.add(product.getAmount().multiply(BigDecimal.valueOf(detail.getQuantity())));
        }
        return total;
    }

}
