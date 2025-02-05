package com.example.ProductService.Schedulers;


import com.example.ProductService.Entitiy.Product;
import com.example.ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductScheduler {

    @Autowired
    private ProductRepository productRepository;

    // This will run every 2 minutes (120000 milliseconds)
    @Scheduled(fixedRate = 120000)
    public void processUnprocessedProducts() {
        // 1) Get all products that have IsProcessed == null
        List<Product> unprocessed = productRepository.findAllByIsProcessed(null);

        // 2) Update each product's status
        for (Product product : unprocessed) {
            product.setIsProcessed("Processed"); // Or product.setIs_processed("Processed");
        }

        // 3) Save the updated entities
        if (!unprocessed.isEmpty()) {
            productRepository.saveAll(unprocessed);
            System.out.println("Processed " + unprocessed.size() + " products.");
        }
    }
}

