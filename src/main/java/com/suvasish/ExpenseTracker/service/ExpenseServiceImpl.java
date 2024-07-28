package com.suvasish.ExpenseTracker.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suvasish.ExpenseTracker.Entity.Expense;
import com.suvasish.ExpenseTracker.dto.ExpenseDTO;
import com.suvasish.ExpenseTracker.repository.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service

public class ExpenseServiceImpl implements ExpenseService{
	private  ExpenseRepository expenseRepository;
	@Autowired
	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		this.expenseRepository=expenseRepository;
	}
	@Override
	public Expense postExpense(ExpenseDTO expenseDTO) {
		if(expenseDTO==null) {
			throw new IllegalArgumentException("expenseDTO is null");
		}
		return saveOrUpdateExpense(new Expense(),expenseDTO);
	}

	private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO) {
		expense.setTitle(expenseDTO.getTitle());
		expense.setDate(expenseDTO.getDate());
		expense.setAmount(expenseDTO.getAmount());
		expense.setCategory(expenseDTO.getCategory());
		expense.setDescription(expenseDTO.getDescription());
		expense.setTitle(expenseDTO.getTitle());
		return expenseRepository.save(expense);
	}
	
	public List<Expense> getAllExpense(){
		return expenseRepository.findAll().stream()
				.sorted(Comparator.comparing(Expense::getDate).reversed())
				.collect(Collectors.toList());
	}
	
	public Expense getExpenseById(Long id) {
		Optional<Expense> optionalExpense =expenseRepository.findById(id);
		if(optionalExpense.isPresent()) {
			return optionalExpense.get();
		}
		else {
			throw new EntityNotFoundException("Expense is not present with id " + id);
		}
	}
	
	public Expense updateExpense(Long id,ExpenseDTO expenseDTO) {
		Optional<Expense> optionalExpense=expenseRepository.findById(id);
		if(optionalExpense.isPresent()) {
			return saveOrUpdateExpense(optionalExpense.get(), expenseDTO);
		}
		else {
			throw new EntityNotFoundException("Expense is not present with id " + id);
		}
	}
	
	public void deleteExpense (Long id) {
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		if(optionalExpense.isPresent()){
			expenseRepository.deleteById(id);
		}
		else{
			throw new EntityNotFoundException("Expense is not present with id"+ id);
		}
	}
}
