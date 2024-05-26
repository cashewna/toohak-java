package dev.khong.toohak.services;

import dev.khong.toohak.models.User;
import dev.khong.toohak.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String register(String email, String password, String nameFirst, String nameLast) throws IllegalAccessException {
        validateEmail(email);
        userRepository.createUser(email, password, nameFirst, nameLast);
        return generateToken();
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private void validateEmail(String email) throws IllegalAccessException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalAccessException("Email already used.");
        }
    }
}
