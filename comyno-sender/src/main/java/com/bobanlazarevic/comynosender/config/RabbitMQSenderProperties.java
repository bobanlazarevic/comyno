package com.bobanlazarevic.comynosender.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "comyno.sender")
@Data
@ToString
public class RabbitMQSenderProperties {

    String messageQueue;
    String messageExchange;
    String messageRoutingKey;

}
