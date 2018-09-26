package com.oodles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.ProfitTable;
import com.oodles.repository.ProfitTableRepository;

@Service
public class ProfitTableService {
	@Autowired
	private ProfitTableRepository profitTableRepository;
	
	public List<ProfitTable> profitList(){
		return profitTableRepository.findAll();
	}
}
