package com.kiss.myselfapi.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiss.myselfapi.bo.LoginRequest;
import com.kiss.myselfapi.bo.RegisterRequest;
import com.kiss.myselfapi.entity.User;
import com.kiss.myselfapi.repository.UserRepository;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger log = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        User user = new User();
        user.setEmail(req.getEmail());
        user.setName(req.getName());
        user.setPassword(req.getPassword());
        user.setAuthType("LOCAL");
        user.setRoles(List.of("USER"));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow();

        if (!user.getPassword().equals(req.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/mail/{email}")
    public ResponseEntity<?> getMail(@PathVariable String email) {
        log.info("Getting user by email: {}", email);
        return ResponseEntity.ok().body(userRepository.findByEmail(email));
    }
}
