package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.ProfitTable;
import com.oodles.service.ProfitTableService;
import com.oodles.util.ResponseHandler;

@RestController
public class ProfitTableController {
	
	@Autowired
	private ProfitTableService profitTableService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("v1/admin/profit/table")
	public Map<String, Object> profitTable(){
		List<ProfitTable> profitList = profitTableService.profitList();
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, profitList);
	}
}
