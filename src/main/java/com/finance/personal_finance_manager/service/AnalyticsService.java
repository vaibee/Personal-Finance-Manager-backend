package com.finance.personal_finance_manager.service;

import com.finance.personal_finance_manager.repository.ExpenseRepository;
import com.finance.personal_finance_manager.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    @Autowired
    private ExpenseRepository expRepo;
    @Autowired
    private IncomeRepository incRepo;

    public Map<String, Object> monthlySummary(
            String username, String month) {

        Map<String, Object> result = new HashMap<>();

        double totalExpenses = expRepo.findByUsername(username).stream()
                .filter(e -> e.getDate().toString().startsWith(month))
                .mapToDouble(e -> e.getAmount())
                .sum();

        double totalIncome = incRepo.findByUsername(username).stream()
                .filter(i -> i.getDate().toString().startsWith(month))
                .mapToDouble(i -> i.getAmount())
                .sum();

        result.put("totalIncome", totalIncome);
        result.put("totalExpenses", totalExpenses);
        result.put("balance", totalIncome - totalExpenses);

        return result;
    }
}