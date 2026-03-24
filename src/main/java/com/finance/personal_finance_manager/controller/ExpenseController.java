package com.finance.personal_finance_manager.controller;

import com.finance.personal_finance_manager.entity.Expense;
import com.finance.personal_finance_manager.service.ExpenseService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping
    public Expense add(@RequestBody Expense exp,
                       @AuthenticationPrincipal UserDetails user) {
        return service.addExpense(exp, user.getUsername());
    }

    @GetMapping
    public List<Expense> list(@AuthenticationPrincipal UserDetails user) {
        return service.getExpenses(user.getUsername());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteExpense(id);
        return "Deleted";
    }
}