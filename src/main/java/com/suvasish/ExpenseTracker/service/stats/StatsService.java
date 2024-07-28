package com.suvasish.ExpenseTracker.service.stats;

import com.suvasish.ExpenseTracker.dto.GraphDTO;
import com.suvasish.ExpenseTracker.dto.StatsDTO;

public interface StatsService {
	GraphDTO getChartData();
	StatsDTO getStats();
}
