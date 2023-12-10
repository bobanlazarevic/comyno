package com.bobanlazarevic.comynosender.job;

import com.bobanlazarevic.comynosender.config.RabbitMQSenderProperties;
import com.bobanlazarevic.comynosender.dto.OutboxMessageDto;
import com.bobanlazarevic.comynosender.entity.OutboxMessage;
import com.bobanlazarevic.comynosender.repository.OutboxRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OutboxSenderJob {

    private static final int SECONDS_IN_PAST = 4;
    private final RabbitMQSenderProperties rabbitMQSenderProperties;
    private final RabbitTemplate rabbitTemplate;

    private final OutboxRepository outboxRepository;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        List<OutboxMessage> outboxEntities = outboxRepository.findByCreatedTimeBefore(LocalDateTime.now().minusSeconds(SECONDS_IN_PAST));
        mapEntitiesToDto(outboxEntities).forEach(dto -> {
            log.info(String.format("Message sent -> %s", dto));
            rabbitTemplate.convertAndSend(rabbitMQSenderProperties.getMessageExchange(), rabbitMQSenderProperties.getMessageRoutingKey(), dto);
        });

        outboxRepository.deleteAll(outboxEntities);
    }

    private List<OutboxMessageDto> mapEntitiesToDto(List<OutboxMessage> outboxMessages) {
        return outboxMessages.stream().map(e ->
                OutboxMessageDto.builder()
                        .message(e.getMessage())
                        .uuid(e.getUuid())
                        .build()
        ).collect(Collectors.toList());
    }

}
