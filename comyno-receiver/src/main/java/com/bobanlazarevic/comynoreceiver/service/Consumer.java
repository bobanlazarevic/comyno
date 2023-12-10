package com.bobanlazarevic.comynoreceiver.service;

import com.bobanlazarevic.comynoreceiver.dto.ReceivedMessageDto;
import com.bobanlazarevic.comynoreceiver.entity.ProcessedMessage;
import com.bobanlazarevic.comynoreceiver.repository.ProcessedMessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class Consumer {
    private final ProcessedMessageRepository processedMessageRepository;

    @RabbitListener(queues = {"${comyno.receiver.message_queue}"})
    public void consume(ReceivedMessageDto receivedMessageDto) {
        if (processedMessageRepository.findByUuid(receivedMessageDto.getUuid()).isPresent()) {
            log.info(String.format("Received message -> %s is already processed", receivedMessageDto));
            return;
        }

        log.info(String.format("Received message -> %s", receivedMessageDto));
        processedMessageRepository.save(ProcessedMessage.builder().uuid(receivedMessageDto.getUuid()).build());
    }
}
