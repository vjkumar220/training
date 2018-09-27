package com.oodles.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.StringConstant;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.services.AdminSupplyService;

@RestController
public class SupplyController {
	@Autowired
	private AdminSupplyService adminSupplyService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/v1/admin/UpdateSupply")

	public Map SupplyUpdation() {
		Map result = null;
		
			result = adminSupplyService.SupplyUpdation();
			return ResponseHandler.generateResponse(HttpStatus.OK, false, StringConstant.Success, null, result);
		
	}

}
