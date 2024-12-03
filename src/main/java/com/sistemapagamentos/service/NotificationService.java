package com.sistemapagamentos.service;

import com.sistemapagamentos.domain.user.User;
import com.sistemapagamentos.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(user.getEmail(), message);

        ResponseEntity<String> notificationReponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if (!(notificationReponse.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Serviço de notificação falhou");
            throw new Exception("Notificação não pode ser enviada");
        }
    }
}
