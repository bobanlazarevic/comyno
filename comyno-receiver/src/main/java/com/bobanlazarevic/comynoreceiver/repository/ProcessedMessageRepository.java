package com.bobanlazarevic.comynoreceiver.repository;

import com.bobanlazarevic.comynoreceiver.entity.ProcessedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedMessageRepository extends JpaRepository<ProcessedMessage, Long> {
    Optional<ProcessedMessage> findByUuid(String uuid);
}
