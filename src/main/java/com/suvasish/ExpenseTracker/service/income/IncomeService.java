package com.suvasish.ExpenseTracker.service.income;

import java.util.List;

import com.suvasish.ExpenseTracker.Entity.Income;
import com.suvasish.ExpenseTracker.dto.IncomeDTO;

public interface IncomeService {
	Income postIncome(IncomeDTO incomeDTO);
	 List<IncomeDTO> getAllIncome();
	 Income updateIncome(Long id,IncomeDTO incomeDTO);
	 Income getIncomeById(Long id);
	 void deleteIncome (Long id);
}
