package com.finance.personal_finance_manager.controller;

import com.finance.personal_finance_manager.dto.LoginRequest;
import com.finance.personal_finance_manager.dto.LoginResponse;
import com.finance.personal_finance_manager.dto.RegisterRequest;
import com.finance.personal_finance_manager.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private  AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        service.register(req);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return service.login(req);
    }
}