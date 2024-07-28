package com.suvasish.ExpenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suvasish.ExpenseTracker.Entity.Expense;
import com.suvasish.ExpenseTracker.dto.ExpenseDTO;
import com.suvasish.ExpenseTracker.service.ExpenseService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/expense")

public class ExpenseController {
	
	private  ExpenseService expenseService;
	@Autowired
	public ExpenseController(ExpenseService expenseService) {
		this.expenseService=expenseService;
	}
	@PostMapping
	public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO dto){
		try {
			Expense createdExpense =expenseService.postExpense(dto);
			if (createdExpense != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllExpense(){
		return ResponseEntity.ok(expenseService.getAllExpense());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getExpenseById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(expenseService.getExpenseById(id));
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateExpense(@PathVariable Long id,@RequestBody ExpenseDTO dto){
		try {
			return ResponseEntity.ok(expenseService.updateExpense(id, dto));
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	    @DeleteMapping("/{id}")
		public ResponseEntity<?> deleteExpense(@PathVariable Long id){
			try {
				expenseService.deleteExpense(id);
				return ResponseEntity.ok(null);
			}catch (EntityNotFoundException ex){
				return ResponseEntity.status(HttpStatus.NOT_FOUND). body(ex.getMessage()) ;
			} catch (Exception e){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
			}
		}
}
