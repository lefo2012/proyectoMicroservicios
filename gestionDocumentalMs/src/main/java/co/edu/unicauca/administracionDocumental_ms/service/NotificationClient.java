package co.edu.unicauca.administracionDocumental_ms.service;

import co.edu.unicauca.administracionDocumental_ms.infra.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "notification-microservice", url = "http://localhost:8081/api/notificaciones")
public interface NotificationClient {

    @PostMapping("/send")
    void sendNotification(@RequestBody NotificationRequest request);
}
