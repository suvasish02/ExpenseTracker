package com.suvasish.ExpenseTracker.Entity;

import java.time.LocalDate;

import com.suvasish.ExpenseTracker.dto.IncomeDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Income {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String title;
	private Integer amount;
	private LocalDate date;
	private String category;
	private String description;
	
	public IncomeDTO getIncomeDTO() {
		IncomeDTO incomeDTO=new IncomeDTO();
		incomeDTO.setId(id);
		incomeDTO.setAmount(amount);
		incomeDTO.setCategory(category);
		incomeDTO.setDate(date);
		incomeDTO.setDescription(description);
		incomeDTO.setTitle(title);
		return incomeDTO;
	}
}
