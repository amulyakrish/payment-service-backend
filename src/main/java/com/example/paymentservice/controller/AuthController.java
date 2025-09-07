package com.example.paymentservice.controller;

import com.example.paymentservice.dto.AuthRequest;
import com.example.paymentservice.entity.User;
import com.example.paymentservice.security.JwtUtil;
import com.example.paymentservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.save(user);
        return "Registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        User user = userService.loadUserByUsername(request.getUsername());
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }
        return "Invalid credentials";
    }
}