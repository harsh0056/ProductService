package com.example.ProductService.Producer;

import com.example.ProductService.Dto.StudentDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaStudentProducer {

    private final KafkaTemplate<StudentDto,StudentDto> kafkaTemplate;


    public KafkaStudentProducer(KafkaTemplate<StudentDto, StudentDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, StudentDto studentDto) {
        kafkaTemplate.send(topic, studentDto);
        System.out.println("Produced message: " + studentDto.toString());
    }
}
