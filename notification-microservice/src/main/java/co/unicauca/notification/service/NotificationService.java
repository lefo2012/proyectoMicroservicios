package co.unicauca.notification.service;

import co.unicauca.notification.infra.dto.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(NotificationRequest request) {
        // Simulación del envío de correo
        logger.info("""
                \n-------------------------------
                SIMULACION DE ENVIO DE CORREO
                De: no-reply@unicauca.edu.co
                Para: {}
                Asunto: {}
                Cuerpo:
                {}
                -------------------------------
                """,
                String.join(", ", request.getEmail()),
                request.getSubject(),
                request.getMessage());
    }
}
