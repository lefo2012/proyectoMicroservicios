package co.edu.unicauca.users_ms.rabbitConfig;

import co.edu.unicauca.users_ms.infra.dto.PersonaDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PersonaProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    public PersonaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPersona(PersonaDto persona) {
        rabbitTemplate.convertAndSend(exchange, routingKey, persona);
        System.out.println("Persona enviada a RabbitMQ: " + persona.getCorreoElectronico());
    }
}
