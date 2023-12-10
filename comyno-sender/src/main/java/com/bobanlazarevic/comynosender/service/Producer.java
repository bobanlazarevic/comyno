package com.bobanlazarevic.comynosender.service;

import com.bobanlazarevic.comynosender.dto.MessageDto;
import com.bobanlazarevic.comynosender.entity.Message;
import com.bobanlazarevic.comynosender.entity.OutboxMessage;
import com.bobanlazarevic.comynosender.repository.MessageRepository;
import com.bobanlazarevic.comynosender.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class Producer {
    private final MessageRepository messageRepository;
    private final OutboxRepository outboxRepository;

    public void handleMessage(MessageDto messageDto) {
        log.info(String.format("Message saved -> %s", messageDto.toString()));
        storeMessage(messageDto.getMessage());
    }

    @Transactional
    public void storeMessage(String message) {
        Message messageEntity = Message.builder().message(message).build();
        OutboxMessage outboxMessageEntity = OutboxMessage.builder().message(message).uuid(UUID.randomUUID().toString()).build();

        messageRepository.save(messageEntity);
        outboxRepository.save(outboxMessageEntity);
    }
}
