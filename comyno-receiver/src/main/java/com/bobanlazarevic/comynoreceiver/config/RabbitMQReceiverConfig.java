package com.bobanlazarevic.comynoreceiver.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitMQReceiverProperties.class)
public class RabbitMQReceiverConfig {

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue(RabbitMQReceiverProperties properties) {
        return new Queue(properties.getMessageQueue());
    }

    @Bean
    public TopicExchange exchange(RabbitMQReceiverProperties properties) {
        return new TopicExchange(properties.getMessageExchange());
    }

    @Bean
    public Binding binding(RabbitMQReceiverProperties properties) {
        return BindingBuilder
                .bind(queue(properties))
                .to(exchange(properties))
                .with(properties.getMessageRoutingKey());
    }

}
