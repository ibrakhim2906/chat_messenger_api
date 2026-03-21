package com.ibrakhim2906.chatmessenger.repos;

import com.ibrakhim2906.chatmessenger.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
