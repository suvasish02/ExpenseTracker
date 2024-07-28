package com.suvasish.ExpenseTracker.service.income;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suvasish.ExpenseTracker.Entity.Expense;
import com.suvasish.ExpenseTracker.Entity.Income;
import com.suvasish.ExpenseTracker.dto.ExpenseDTO;
import com.suvasish.ExpenseTracker.dto.IncomeDTO;
import com.suvasish.ExpenseTracker.repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IncomeServiceImpl implements IncomeService{
	private IncomeRepository incomeRepository;
	@Autowired
	public IncomeServiceImpl(IncomeRepository incomeRepository) {
		this.incomeRepository=incomeRepository;
	}
	
	public Income postIncome(IncomeDTO incomeDTO) {
		if(incomeDTO==null) {
			throw new IllegalArgumentException("incomeDTO is null");
		}
		return saveOrUpdateIncome(new Income(),incomeDTO);
	}

	public Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO) {
		// TODO Auto-generated method stub
		income.setTitle(incomeDTO.getTitle());
		income.setAmount(incomeDTO.getAmount());
		income.setCategory(incomeDTO.getCategory());
		income.setDate(incomeDTO.getDate());
		income.setDescription(incomeDTO.getDescription());
		return incomeRepository.save(income);
	}
	
	public List<IncomeDTO> getAllIncome(){
		return incomeRepository.findAll().stream()
				.sorted(Comparator.comparing(Income::getDate).reversed())
				.map(Income::getIncomeDTO)
				.collect(Collectors.toList());
	}
	
	public Income updateIncome(Long id,IncomeDTO incomeDTO) {
		Optional<Income> optionalIncome=incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);
		}
		else {
			throw new EntityNotFoundException("Income is not present with id " + id);
		}
	}
	
	public Income getIncomeById(Long id) {
		Optional<Income> optionalIncome =incomeRepository.findById(id);
		if(optionalIncome.isPresent()) {
			return optionalIncome.get();
		}
		else {
			throw new EntityNotFoundException("Income is not present with id " + id);
		}
	}
	
	public void deleteIncome (Long id) {
		Optional<Income> optionalIncome = incomeRepository.findById(id);
		if(optionalIncome.isPresent()){
			incomeRepository.deleteById(id);
		}
		else{
			throw new EntityNotFoundException("Income is not present with id"+ id);
		}
	}
	
}
