package com.example.ProductService.Consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "quickstart-events", groupId = "console-consumer-3678")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}

