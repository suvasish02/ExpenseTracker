package com.suvasish.ExpenseTracker.dto;

import com.suvasish.ExpenseTracker.Entity.Expense;
import com.suvasish.ExpenseTracker.Entity.Income;

import lombok.Data;

@Data
public class StatsDTO {
	private Double income;
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Double getExpense() {
		return expense;
	}
	public void setExpense(Double expense) {
		this.expense = expense;
	}
	public Income getLatestIncome() {
		return latestIncome;
	}
	public void setLatestIncome(Income latestIncome) {
		this.latestIncome = latestIncome;
	}
	public Expense getLatesExpense() {
		return LatesExpense;
	}
	public void setLatesExpense(Expense latesExpense) {
		LatesExpense = latesExpense;
	}
	private Double expense;
	private Income latestIncome;
	private Expense LatesExpense;
	private Double balance;
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getMinIncome() {
		return minIncome;
	}
	public void setMinIncome(Double minIncome) {
		this.minIncome = minIncome;
	}
	public Double getMaxIncome() {
		return maxIncome;
	}
	public void setMaxIncome(Double maxIncome) {
		this.maxIncome = maxIncome;
	}
	public Double getMinExpense() {
		return minExpense;
	}
	public void setMinExpense(Double minExpense) {
		this.minExpense = minExpense;
	}
	public Double getMaxExpense() {
		return maxExpense;
	}
	public   void setMaxExpense(Double maxExpense) {
		this.maxExpense = maxExpense;
	}
	private Double minIncome;
	private Double maxIncome;
	private Double minExpense;
	private Double maxExpense;
}
