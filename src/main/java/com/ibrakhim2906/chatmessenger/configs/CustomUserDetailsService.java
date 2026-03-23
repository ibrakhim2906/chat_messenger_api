package com.ibrakhim2906.chatmessenger.configs;

import com.ibrakhim2906.chatmessenger.models.User;
import com.ibrakhim2906.chatmessenger.repos.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private CustomUserDetails customUserDetails;
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (repo.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User do not exist");
        }

        User user = repo.findByEmail(email);

        return new CustomUserDetails(user);
    }
}
