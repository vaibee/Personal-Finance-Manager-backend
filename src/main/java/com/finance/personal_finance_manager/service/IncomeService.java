package com.finance.personal_finance_manager.service;

import com.finance.personal_finance_manager.entity.Income;
import com.finance.personal_finance_manager.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    @Autowired
    private IncomeRepository repo;

    public Income addIncome(Income income, String username) {
        income.setUsername(username);
        return repo.save(income);
    }

    public List<Income> getIncome(String username) {
        return repo.findByUsername(username);
    }

    public void deleteIncome(Long id) {
        repo.deleteById(id);
    }
}