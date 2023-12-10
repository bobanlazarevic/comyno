package com.bobanlazarevic.comynosender.repository;

import com.bobanlazarevic.comynosender.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
