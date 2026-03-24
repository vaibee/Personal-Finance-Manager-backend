package com.finance.personal_finance_manager.controller;

import com.finance.personal_finance_manager.service.AnalyticsService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    @Autowired
    private AnalyticsService service;

    @GetMapping("/summary/{month}")
    public Map<String, Object> summary(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable String month) {

        return service.monthlySummary(user.getUsername(), month);
    }
}