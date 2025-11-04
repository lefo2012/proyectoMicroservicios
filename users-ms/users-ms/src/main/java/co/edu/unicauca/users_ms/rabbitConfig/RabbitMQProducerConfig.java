package co.edu.unicauca.users_ms.rabbitConfig;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;

@Configuration
public class RabbitMQProducerConfig {

    @Value("${rabbitmq.exchange1}")
    private String exchange;

    @Value("${rabbitmq.routingKey1}")
    private String routingKey;

    @Value("${rabbitmq.queue1}")
    private String queue;

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }

    @Bean
    public Queue personaQueue() {
        return new Queue(queue, true);
    }

    @Bean
    public TopicExchange personaExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(Queue personaQueue, TopicExchange personaExchange) {
        return BindingBuilder.bind(personaQueue).to(personaExchange).with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }



}