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
@RequestMapping(value = "/deposit")
public class DepositController {
	Logger log = LoggerFactory.getLogger(DepositController.class);

	@Autowired
	private DepositService depositService;

	private Map result = new HashMap<>();

	// Generating deposit request for fiat wallet

	@PostMapping(value = "/create-fiat-deposit-request")
	public Map createFiatDeposit(@Valid @RequestBody FiatDepositDto depositDto) {
		log.info("In deposit controller", depositDto);
		try {
			result = depositService.fiatDeposit(depositDto);
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (Exception e) {
			log.info("in catch fiat deposit");
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

	// Getting all pending fiat deposit request

	@GetMapping(value = "/get-pending-request")
	public List getAllPendingReq() {
		List depositsList = depositService.getAllPendingRequest();
		return depositsList;
	}

	// Approval of the deposit request

	@PostMapping(value = "/approve-update-fiat-deposit-request")
	public Map approveFiatDepositRequest(@Valid @RequestBody ApprovalDto approvalDto) {
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

	// Creating the crypto deposit request

	@PostMapping(value = "/create-crypto-deposit-request")
	public Map createCryptoDeposit(@Valid @RequestBody CryptoDepositDto cryptoDepositDto) {
		try {
			result = depositService.cryptoDeposit(cryptoDepositDto);
			log.info("result", result);
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	// Approve and update balance crypto deposit request

	@PostMapping(value = "/approve-crypto-deposit-request")
	public Map approveCryptoDepositRequest(@Valid @RequestBody CryptoApprovalDto cryptoApprovalDto) {
		try {
			result = depositService.approveCryptoRequest(cryptoApprovalDto);
			log.info("result", result);
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
}
