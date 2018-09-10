package com.oodles.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.FiatDepositDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.DepositService;
import com.oodles.util.ResponseHandler;

@RestController
@RequestMapping(value = "/v1")
public class DepositController {
	Logger log = LoggerFactory.getLogger(DepositController.class);

	@Autowired
	private DepositService depositService;

	private Map result = null;

	// Generating deposit request for fiat wallet
	@GetMapping(value = "/fiatDeposit")
	public Map createFiatDeposit(@RequestBody FiatDepositDto depositDto) {
			log.info("In deposit controller", depositDto);
		try {
			result = depositService.fiatDeposit(depositDto);
			log.info("result", result);
			if (result.containsKey("success")) {
				log.info("in if fiat deposit");
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			} else {
				log.info("in else fiat deposit");
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
			}
		} catch (ResourceNotFoundException e) {
			log.info("in catch fiat deposit");
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

}
