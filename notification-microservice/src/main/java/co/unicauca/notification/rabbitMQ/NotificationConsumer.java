package co.unicauca.notification.rabbitMQ;

import co.unicauca.notification.infra.dto.NotificationRequest;
import co.unicauca.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "notification_queue")
    public void recibirNotificacion(NotificationRequest notification) {
        try {
            System.out.println("Notificación recibida desde RabbitMQ: " + notification.getEmail());
            notificationService.sendNotification(notification);
        } catch (Exception e) {
            System.err.println("Error al procesar notificación: " + e.getMessage());
        }
    }
}
