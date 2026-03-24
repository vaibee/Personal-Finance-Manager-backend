package com.finance.personal_finance_manager.repository;

import com.finance.personal_finance_manager.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUsername(String username);

}