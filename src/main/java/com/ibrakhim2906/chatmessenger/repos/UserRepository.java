package com.ibrakhim2906.chatmessenger.repos;

import com.ibrakhim2906.chatmessenger.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
