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


import com.suvasish.ExpenseTracker.Entity.Income;
import com.suvasish.ExpenseTracker.dto.ExpenseDTO;
import com.suvasish.ExpenseTracker.dto.IncomeDTO;
import com.suvasish.ExpenseTracker.service.income.IncomeService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/income")
public class IncomeController {
	private IncomeService incomeService;
	@Autowired
	public IncomeController(IncomeService incomeService) {
		this.incomeService = incomeService;
	}
	
	@PostMapping
	public ResponseEntity<?> postIncome(@RequestBody IncomeDTO dto){
		try {
			Income createdIncome =incomeService.postIncome(dto);
			if (createdIncome != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		
	}
	@GetMapping("/allincome")
	public ResponseEntity<?> getAllIncome(){
		return ResponseEntity.ok(incomeService.getAllIncome());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateIncome(@PathVariable Long id,@RequestBody IncomeDTO dto){
		try {
			return ResponseEntity.ok(incomeService.updateIncome(id, dto));
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getIncomeById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(incomeService.getIncomeById(id));
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	

    @DeleteMapping("/{id}")
	public ResponseEntity<?> deleteIncome(@PathVariable Long id){
		try {
			incomeService.deleteIncome(id);
			return ResponseEntity.ok(null);
		}catch (EntityNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND). body(ex.getMessage()) ;
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
		}
	}
	
}
