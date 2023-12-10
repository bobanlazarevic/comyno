package com.bobanlazarevic.comynosender.repository;

import com.bobanlazarevic.comynosender.entity.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxMessage, Long> {
    List<OutboxMessage> findByCreatedTimeBefore(LocalDateTime localDateTime);
}
