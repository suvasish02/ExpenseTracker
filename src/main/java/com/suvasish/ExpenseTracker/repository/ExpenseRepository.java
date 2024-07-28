package com.suvasish.ExpenseTracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suvasish.ExpenseTracker.Entity.Expense;
import com.suvasish.ExpenseTracker.Entity.Income;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	List<Expense> findByDateBetween(LocalDate startDate,LocalDate endDate);
	@Query("Select sum(e.amount) from Expense e")
	Double sumAllAmounts();
	Optional<Expense> findFirstByOrderByDateDesc();
}
