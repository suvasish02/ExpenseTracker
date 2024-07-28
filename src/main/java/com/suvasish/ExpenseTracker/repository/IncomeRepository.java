package com.suvasish.ExpenseTracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.suvasish.ExpenseTracker.Entity.Income;
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{
	List<Income> findByDateBetween(LocalDate startDate,LocalDate endDate);
	@Query("Select sum(i.amount) from Income i")
	Double sumAllAmounts();
	Optional<Income> findFirstByOrderByDateDesc();
}
