package com.ibrakhim2906.chatmessenger.services;

import com.ibrakhim2906.chatmessenger.configs.CustomUserDetails;
import com.ibrakhim2906.chatmessenger.models.User;
import com.ibrakhim2906.chatmessenger.repos.UserRepository;
import com.ibrakhim2906.chatmessenger.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository repo, PasswordEncoder passwordEncoder, JwtService service) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=service;
    }

    public void register(String username, String email, String password) {
        if (repo.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);

        repo.save(user);

    }

    public String login(String email, String password) {
        if (!repo.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User do not exist");
        }

        User user = repo.findByEmail(email);

        boolean isPasswordCorrect = passwordEncoder.matches(password, user.getPassword());

        if (!isPasswordCorrect) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(userDetails);

        return token;
    }
}

