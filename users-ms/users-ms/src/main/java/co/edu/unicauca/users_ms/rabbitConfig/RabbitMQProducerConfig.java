package co.edu.unicauca.users_ms.rabbitConfig;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;





@Configuration
public class RabbitMQProducerConfig {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Bean
    public Queue personaQueue() {
        return new Queue("persona_queue", true);
    }

    @Bean
    public TopicExchange personaExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(Queue personaQueue, TopicExchange personaExchange) {
        return BindingBuilder.bind(personaQueue).to(personaExchange).with(routingKey);
    }
}