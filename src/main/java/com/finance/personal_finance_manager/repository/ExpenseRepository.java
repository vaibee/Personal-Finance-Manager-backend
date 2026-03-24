package com.finance.personal_finance_manager.repository;

import com.finance.personal_finance_manager.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUsername(String username);

}