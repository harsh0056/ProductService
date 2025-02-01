package com.example.ProductService.Controller;

import com.example.ProductService.Dto.StudentDto;
import com.example.ProductService.Producer.KafkaProducer;
import com.example.ProductService.Producer.KafkaStudentProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    private final KafkaStudentProducer kafkaStudentProducer;

    public KafkaController(KafkaProducer kafkaProducer, KafkaStudentProducer kafkaStudentProducer) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaStudentProducer = kafkaStudentProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducer.sendMessage("quickstart-events", message);
        return "Message sent: " + message;
    }

    @PostMapping("/send/student")
    public String sendMessageToStudent(@RequestBody StudentDto studentDto) {
        kafkaStudentProducer.sendMessage("my-student-topic", studentDto);
        return "Message sent: " + studentDto;

    }

}
