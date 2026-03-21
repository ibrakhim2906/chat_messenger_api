package com.ibrakhim2906.chatmessenger.repos;

import com.ibrakhim2906.chatmessenger.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
