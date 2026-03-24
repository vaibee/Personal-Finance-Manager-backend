package com.finance.personal_finance_manager.service;

import com.finance.personal_finance_manager.entity.Budget;
import com.finance.personal_finance_manager.repository.BudgetRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    @Autowired
    private BudgetRepository repo;

    public Budget setBudget(Budget budget, String username) {
        budget.setUsername(username);
        return repo.save(budget);
    }

    public List<Budget> listBudgets(String username) {
        return repo.findByUsername(username);
    }
}