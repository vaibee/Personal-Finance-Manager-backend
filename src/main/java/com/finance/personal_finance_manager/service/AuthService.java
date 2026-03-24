package com.finance.personal_finance_manager.service;

import com.finance.personal_finance_manager.dto.LoginRequest;
import com.finance.personal_finance_manager.dto.LoginResponse;
import com.finance.personal_finance_manager.dto.RegisterRequest;
import com.finance.personal_finance_manager.entity.User;
import com.finance.personal_finance_manager.repository.UserRepository;
import com.finance.personal_finance_manager.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(RegisterRequest req) {
        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        repo.save(u);
    }

    public LoginResponse login(LoginRequest req) {

        User user = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());


        return new LoginResponse(token, user);
    }
}