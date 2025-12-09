package co.unicauca.notification.controller;

import co.unicauca.notification.infra.dto.NotificationRequest;
import co.unicauca.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        try {
            notificationService.sendNotification(request);
            return ResponseEntity.ok("Notificación simulada correctamente para: " + String.join(", ", request.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al procesar la notificación: " + e.getMessage());
        }
    }
}
