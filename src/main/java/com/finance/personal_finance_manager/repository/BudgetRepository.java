package com.finance.personal_finance_manager.repository;

import com.finance.personal_finance_manager.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByUsername(String username);

}