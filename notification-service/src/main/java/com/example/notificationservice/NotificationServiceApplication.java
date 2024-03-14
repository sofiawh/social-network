package com.example.notificationservice;

import com.example.notificationservice.dto.UserDTO;
import io.micrometer.observation.Observation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    private final FriendServiceClient friendServiceClient;

    public NotificationServiceApplication(FriendServiceClient friendServiceClient) {
        this.friendServiceClient = friendServiceClient;
    }
//    private final RestTemplate restTemplate;
//
//    public NotificationServiceApplication(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(PostPlacedEvent postPlacedEvent) {
        log.info("TraceId- {}, Received Notification for Order - {}", postPlacedEvent.getPostNumber());
        System.out.println("****** postPlacedEvent****"+postPlacedEvent.getUserId());
        String userId = postPlacedEvent.getUserId();// "1"; // Remplacez par l'ID utilisateur approprié

        // Appelez le service externe via Feign pour récupérer la liste des amis
        List<UserDTO> friends = friendServiceClient.getAllFriendsProfile(userId);

        // Envoyez des notifications à tous les amis
        for (UserDTO friend : friends) {
            envoyerNotification(friend);
        }
    }

    private void envoyerNotification(UserDTO ami) {
        // Implémentez ici la logique pour envoyer une notification à l'ami
        // Cela peut impliquer l'envoi d'un e-mail, d'une notification push, etc.
        log.info("Notification envoyée à l'ami : {}", ami.getEmail());
    }

//    @KafkaListener(topics = "notificationTopic")
//    public void handleNotification(PostPlacedEvent postPlacedEvent) {
//       /* Observation.createNotStarted("on-message", this.observationRegistry).observe(() -> {
//            log.info("Got message <{}>", postPlacedEvent);*/
//            log.info("TraceId- {}, Received Notification for Order - {}", /*this.tracer.currentSpan().context().traceId(),*/
//                    postPlacedEvent.getPostNumber());
//
//        // L'URL de l'API définie dans votre contrôleur
//        String url = "http://localhost:8222/api/friend/profiles/all"; //"http://localhost:port/api/friend/profiles/all";
//    String userId = "1";
//// Créez un objet HttpHeaders pour ajouter l'en-tête 'id'
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("id", userId); // Remplacez 'userId' par la valeur appropriée
//
//        // Créez un objet HttpHeaders pour ajouter l'en-tête d'autorisation
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer votre_token_d'authentification");
//
//// Créez un objet HttpEntity avec les en-têtes
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//// Créez un objet HttpEntity avec les en-têtes
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//// Appelez la méthode exchange de RestTemplate pour obtenir la réponse
//        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                entity,
//                new ParameterizedTypeReference<List<UserDTO>>() {});
//
//// Récupérez la liste des amis depuis la réponse
//        List<UserDTO> friends = response.getBody();
//
//        // Envoyer des notifications aux amis
//        for (UserDTO friend : friends) {
//            // Envoyer la notification à chaque ami
//            envoyerNotification(friend);
//        }
//
//    }
    // Méthode pour envoyer la notification à un ami
//    private void envoyerNotification(UserDTO ami) {
//        // Implémentez ici la logique pour envoyer une notification à l'ami
//        // Cela peut impliquer l'envoi d'un e-mail, d'une notification push, etc.
//        log.info("Notification envoyée à l'ami : {}", ami.getEmail());
//    }
//    private Long getUserIdFromPostNumber(String postNumber) {
//        // Implémentez la logique pour récupérer l'ID de l'utilisateur à partir du numéro de poste
//        return 123L; // Exemple de retour d'un ID fictif
//    }
}
//    }
//}
