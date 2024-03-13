package com.example.notificationservice;

import io.micrometer.observation.Observation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(PostPlacedEvent postPlacedEvent) {
       /* Observation.createNotStarted("on-message", this.observationRegistry).observe(() -> {
            log.info("Got message <{}>", postPlacedEvent);*/
            log.info("TraceId- {}, Received Notification for Order - {}", /*this.tracer.currentSpan().context().traceId(),*/
                    postPlacedEvent.getPostNumber());
        //});
        // send out an email notification
    }
}
