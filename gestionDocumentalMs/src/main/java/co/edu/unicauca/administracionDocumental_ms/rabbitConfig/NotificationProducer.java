package co.edu.unicauca.administracionDocumental_ms.rabbitConfig;

import co.edu.unicauca.administracionDocumental_ms.infra.dto.NotificationRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange1}")
    private String exchange;

    @Value("${rabbitmq.routingKey1}")
    private String routingKey;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarNotificacion(NotificationRequest notification) {
        rabbitTemplate.convertAndSend(exchange, routingKey, notification);
        System.out.println("Notificación enviada a RabbitMQ para: " + notification.getEmail());
    }
}
