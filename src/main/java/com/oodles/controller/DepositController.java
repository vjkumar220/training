package com.oodles.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.deposit.FiatDeposit;
import com.oodles.dto.ApprovalDto;
import com.oodles.dto.CryptoApprovalDto;
import com.oodles.dto.CryptoDepositDto;
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

	private Map result = new HashMap<>();

	// Generating deposit request for fiat wallet

	@PostMapping(value = "/fiatDepositRequest")
	public Map createFiatDeposit(@Valid @RequestBody FiatDepositDto depositDto) {
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
		} catch (Exception e) {
			log.info("in catch fiat deposit");
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

	// Getting all pending fiat deposit request
	@GetMapping(value = "/getPendingRequest")
	public List getAllPendingReq() {
		List depositsList = depositService.getAllPendingRequest();
		return depositsList;
	}

	// Approval of the deposit request
	@PostMapping(value = "/approvedFiatDepositRequest")
	public Map approvalDepositRequest(@Valid @RequestBody ApprovalDto approvalDto) {
		try {
			result = depositService.approveDeposit(approvalDto);
			log.info("result", result);
			if (result.containsKey("success")) {
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
			}
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
	
	//Creating the crypto deposit request
	
	@PostMapping(value = "/cryptoDepositRequest")
	public Map cryptoDeposit(@Valid @RequestBody CryptoDepositDto cryptoDepositDto) {
		try {
			result = depositService.cryptoDeposit(cryptoDepositDto);
			log.info("result", result);
			if (result.containsKey("success")) {
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
			}
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
	
	@PostMapping(value = "/approvedCryptoDepositRequest")
	public Map approvalCryptoDepositRequest(@Valid @RequestBody CryptoApprovalDto cryptoApprovalDto) {
		try {
			result = depositService.approveCryptoRequest(cryptoApprovalDto);
			log.info("result", result);
			if (result.containsKey("success")) {
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
			}
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
}
