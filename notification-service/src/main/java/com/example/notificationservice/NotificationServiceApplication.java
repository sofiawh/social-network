package com.example.notificationservice;

import com.example.notificationservice.dto.UserDTO;
import com.example.notificationservice.email.EmailSenderService;
import com.example.notificationservice.service.impl.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import java.util.List;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {


    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class, args);
    }



}
