package com.suvasish.ExpenseTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suvasish.ExpenseTracker.dto.GraphDTO;
import com.suvasish.ExpenseTracker.service.stats.StatsService;

@RestController
@RequestMapping("/stats")
public class StatsController {
	private StatsService statsService;
	@Autowired
	public StatsController(StatsService statsService) {
		this.statsService=statsService;
	}
	@GetMapping("/chart")
	public ResponseEntity<GraphDTO> getChartDetails(){
		return ResponseEntity.ok(statsService.getChartData());
	}
	@GetMapping
	public ResponseEntity<?> getStats(){
		return ResponseEntity.ok(statsService.getStats());
	}
	
}
