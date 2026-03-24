package com.finance.personal_finance_manager.controller;

import com.finance.personal_finance_manager.entity.Income;
import com.finance.personal_finance_manager.service.IncomeService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
@RequiredArgsConstructor
public class IncomeController {

    @Autowired
    private IncomeService service;

    @PostMapping
    public Income add(@RequestBody Income income,
                      @AuthenticationPrincipal UserDetails user) {
        return service.addIncome(income, user.getUsername());
    }

    @GetMapping
    public List<Income> list(@AuthenticationPrincipal UserDetails user) {
        return service.getIncome(user.getUsername());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteIncome(id);
        return "Deleted";
    }
}