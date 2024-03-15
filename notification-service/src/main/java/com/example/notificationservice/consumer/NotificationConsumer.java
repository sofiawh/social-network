package com.example.notificationservice.consumer;

import com.example.notificationservice.FriendServiceClient;
import com.example.notificationservice.PostPlacedEvent;
import com.example.notificationservice.dto.UserDTO;
import com.example.notificationservice.email.EmailSenderService;
import com.example.notificationservice.service.impl.NotificationService;
import com.nabilaitnacer.servicepost.dto.PostProducerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NotificationConsumer {

    private final FriendServiceClient friendServiceClient;
    @Autowired
    private EmailSenderService emailSenderService;
    public NotificationConsumer(FriendServiceClient friendServiceClient, NotificationService notificationService) {
        this.friendServiceClient = friendServiceClient;
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(PostPlacedEvent postPlacedEvent) {

        log.info("TraceId- {}, Received Notification for Order - {}", postPlacedEvent.getPostNumber());
        String userId = postPlacedEvent.getUserId();// "1"; // Remplacez par l'ID utilisateur approprié

        // Appelez le service externe via Feign pour récupérer la liste des amis
        List<UserDTO> friends = friendServiceClient.getAllFriendsProfile(userId);

        // Envoyez des notifications à tous les amis
        for (UserDTO friend : friends) {
            envoyerNotification(friend);
        }


    }

    private void envoyerNotification(UserDTO friend) {
        log.info("Notification envoyée à l'ami : {}", friend.getEmail());

        emailSenderService.sendEmail(friend.getEmail(),
                "Post created",
                "This is notification about post created"
        );


    }

//    @KafkaListener(topics = "post-topic", groupId = "myGroup")
//    public void consumerMsg(PostProducerDto post)
//    {
//        log.info(String.format("Consuming the message from post-topic topic: %s", post));
//        // save in db
//    }
}
