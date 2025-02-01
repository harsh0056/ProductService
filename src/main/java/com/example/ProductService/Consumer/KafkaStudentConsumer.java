package com.example.ProductService.Consumer;

import com.example.ProductService.Dto.StudentDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaStudentConsumer {

    @KafkaListener(topics="my-student-topic", groupId= "my-student-group")
    public void consumeMessage(StudentDto student) {
        System.out.println("Consumed message for student : " + student.toString());
    }

}
