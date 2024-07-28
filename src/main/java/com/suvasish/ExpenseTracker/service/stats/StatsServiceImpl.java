package com.suvasish.ExpenseTracker.service.stats;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.suvasish.ExpenseTracker.Entity.Expense;
import com.suvasish.ExpenseTracker.Entity.Income;
import com.suvasish.ExpenseTracker.dto.GraphDTO;
import com.suvasish.ExpenseTracker.dto.StatsDTO;
import com.suvasish.ExpenseTracker.repository.ExpenseRepository;
import com.suvasish.ExpenseTracker.repository.IncomeRepository;

@Service

public class StatsServiceImpl implements StatsService{
	private IncomeRepository incomeRepository;
	private ExpenseRepository expenseRepository;
	
	public GraphDTO getChartData() {
		GraphDTO graphDTO=new GraphDTO();
		LocalDate endDate=LocalDate.now();
		LocalDate startDate =endDate.minusDays(27);
		graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
		graphDTO.setIncomelist(incomeRepository.findByDateBetween(startDate, endDate));
		return graphDTO;
	}
	
	public StatsDTO getStats() {
		Double totalIncome=incomeRepository.sumAllAmounts();
		Double totalExpense=expenseRepository.sumAllAmounts();
		StatsDTO statsDTO=new StatsDTO();
		statsDTO.setExpense(totalExpense);
		statsDTO.setIncome(totalIncome);
		Optional<Income> optionalIncome=incomeRepository.findFirstByOrderByDateDesc();
		Optional<Expense> optionalExpense=expenseRepository.findFirstByOrderByDateDesc();
		optionalExpense.ifPresent(statsDTO::setLatesExpense);
		optionalIncome.ifPresent(statsDTO::setLatestIncome);
		statsDTO.setBalance(totalIncome-totalExpense);
		List<Expense> expenseList=expenseRepository.findAll();
		List<Income> incomeList=incomeRepository.findAll();
		OptionalDouble minIncome=incomeList.stream().mapToDouble(Income::getAmount).min();
		OptionalDouble maxIncome=incomeList.stream().mapToDouble(Income::getAmount).max();
		OptionalDouble minExpense=expenseList.stream().mapToDouble(Expense::getAmount).min();
		OptionalDouble maxExpense=expenseList.stream().mapToDouble(Expense::getAmount).max();
		statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
		statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);
		statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
		statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);
		return statsDTO;
	}
}
