package dev.khong.toohak.controllers;

import dev.khong.toohak.dto.UserRegistrationRequest;
import dev.khong.toohak.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/admin/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, String>> register(@RequestBody UserRegistrationRequest user) throws IllegalAccessException {
        String token = authService.register(user.getEmail(), user.getPassword(), user.getNameFirst(), user.getNameLast());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
