package com.finance.personal_finance_manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.personal_finance_manager.repository.BudgetRepository;
import com.finance.personal_finance_manager.repository.ExpenseRepository;
import com.finance.personal_finance_manager.repository.IncomeRepository;
import com.finance.personal_finance_manager.service.AiInsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiInsightController {

    private final ExpenseRepository expenses;
    private final IncomeRepository incomes;
    private final BudgetRepository budgets;
    private final AiInsightService ai;

    @Autowired
    private ObjectMapper mapper;

    public AiInsightController(
            ExpenseRepository expenses,
            IncomeRepository incomes,
            BudgetRepository budgets,
            AiInsightService ai
    ) {
        this.expenses = expenses;
        this.incomes = incomes;
        this.budgets = budgets;
        this.ai = ai;
    }

    @GetMapping("/insights/{username}")
    public String getInsights(@PathVariable String username) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("expenses", expenses.findByUsername(username));
        map.put("income", incomes.findByUsername(username));
        map.put("budgets", budgets.findByUsername(username));

        String json = mapper.writeValueAsString(map);

        return ai.generateInsights(json);
    }
}