package com.finance.personal_finance_manager.service;

import com.finance.personal_finance_manager.entity.Expense;
import com.finance.personal_finance_manager.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    public Expense addExpense(Expense exp, String username) {
        exp.setUsername(username);
        return repo.save(exp);
    }

    public List<Expense> getExpenses(String username) {
        return repo.findByUsername(username);
    }

    public void deleteExpense(Long id) {
        repo.deleteById(id);
    }
}