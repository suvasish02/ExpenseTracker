package com.suvasish.ExpenseTracker.dto;

import java.util.List;

import com.suvasish.ExpenseTracker.Entity.Expense;
import com.suvasish.ExpenseTracker.Entity.Income;

import lombok.Data;

@Data
public class GraphDTO {
	private List<Expense> expenseList;
	public List<Expense> getExpenseList() {
		return expenseList;
	}
	public void setExpenseList(List<Expense> expenseList) {
		this.expenseList = expenseList;
	}
	public List<Income> getIncomelist() {
		return incomelist;
	}
	public void setIncomelist(List<Income> incomelist) {
		this.incomelist = incomelist;
	}
	private List<Income> incomelist;
}
