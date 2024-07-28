package com.suvasish.ExpenseTracker.service;

import java.util.List;

import com.suvasish.ExpenseTracker.Entity.Expense;
import com.suvasish.ExpenseTracker.dto.ExpenseDTO;

public interface ExpenseService {
	Expense postExpense(ExpenseDTO expenseDTO);
	List<Expense> getAllExpense();
	Expense getExpenseById(Long id);
	Expense updateExpense(Long id,ExpenseDTO expenseDTO);
	void deleteExpense (Long id);
}
