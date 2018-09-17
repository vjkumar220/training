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

import com.oodles.domain.FiatDeposit;
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

	/**
	 * Generating deposit request for fiat wallet
	 * 
	 * @param depositDto
	 * @return
	 */

	@PostMapping(value = "/create/fiat/deposit/request")
	public Map<String, Object> createFiatDeposit(@Valid @RequestBody FiatDepositDto depositDto) {
		Map<Object, Object> result = new HashMap<>();
		try {
			result = depositService.fiatDeposit(depositDto);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (Exception e) {
			log.info("in catch fiat deposit");
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

	/**
	 * Getting all pending fiat deposit request
	 * 
	 * @return
	 */

	@GetMapping(value = "/pending/request")
	public Map<String, Object> getAllPendingReq() {

		Map<Object, Object> result = new HashMap<>();
		try {
			List<FiatDeposit> pendingList = depositService.getAllPendingRequest();
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, pendingList);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}

	}

	/**
	 * Approval of the deposit request
	 * 
	 * @param approvalDto
	 * @return
	 */

	@PostMapping(value = "/approve/update/fiat/deposit/request")
	public Map<String, Object> approveFiatDepositRequest(@Valid @RequestBody ApprovalDto approvalDto) {
		Map<Object, Object> result = new HashMap<>();
		try {
			result = depositService.approveDeposit(approvalDto);
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	/**
	 * Creating the crypto deposit request
	 * 
	 * @param cryptoDepositDto
	 * @return
	 */

	@PostMapping(value = "/create/crypto/deposit/request")
	public Map<String, Object> createCryptoDeposit(@Valid @RequestBody CryptoDepositDto cryptoDepositDto) {
		Map<Object, Object> result = new HashMap<>();
		try {
			result = depositService.cryptoDeposit(cryptoDepositDto);
			log.info("result", result);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

	/**
	 *  Approve and update balance crypto deposit request
	 * @param cryptoApprovalDto
	 * @return
	 */

	@PostMapping(value = "/approve/crypto/deposit/request")
	public Map<String, Object> approveCryptoDepositRequest(@Valid @RequestBody CryptoApprovalDto cryptoApprovalDto) {
		Map<Object, Object> result = new HashMap<>();
		try {
			result = depositService.approveCryptoRequest(cryptoApprovalDto);
			log.info("result", result);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
}
