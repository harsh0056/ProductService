package com.example.ProductService.Configuration;


import com.example.ProductService.Dto.StudentDto;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, StudentDto> studentConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "my-student-group");
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(StudentDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StudentDto> studentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StudentDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(studentConsumerFactory());
        return factory;
    }
}

