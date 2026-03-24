package com.finance.personal_finance_manager.controller;

import com.finance.personal_finance_manager.entity.Budget;
import com.finance.personal_finance_manager.service.BudgetService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    @Autowired
    private BudgetService service;

    @PostMapping
    public Budget setBudget(@RequestBody Budget budget,
                            @AuthenticationPrincipal UserDetails user) {
        return service.setBudget(budget, user.getUsername());
    }

    @GetMapping
    public List<Budget> list(@AuthenticationPrincipal UserDetails user) {
        return service.listBudgets(user.getUsername());
    }
}