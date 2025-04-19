package com.ecommerce.inventoryservice;

import com.ecommerce.inventoryservice.domain.entity.ProductEntity;
import com.ecommerce.inventoryservice.domain.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


@Component
public class DatabaseFiller implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DatabaseFiller(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void fillDatabase() {
        ProductEntity newProduct = new ProductEntity(
                "Woda",
                BigDecimal.valueOf(2.50),
                7
        );
        productRepository.save(newProduct);
    }

    @Override
    @Transactional
    public void run(String... args) {
        fillDatabase();
    }
}
