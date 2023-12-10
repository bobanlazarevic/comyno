package com.bobanlazarevic.comynoreceiver.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "comyno.receiver")
@Data
@ToString
public class RabbitMQReceiverProperties {

    String messageQueue;
    String messageExchange;
    String messageRoutingKey;

}
