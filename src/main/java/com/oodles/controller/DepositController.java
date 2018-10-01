package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.FiatDeposit;
import com.oodles.dto.ApprovalDto;
import com.oodles.dto.FiatDepositDto;
import com.oodles.service.DepositService;
import com.oodles.util.ResponseHandler;


@RestController
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
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "v1//user/fiat/deposit")
	public Map<String, Object> createFiatDeposit(@Valid @RequestBody FiatDepositDto depositDto) {
		Map<Object, Object> result = new HashMap<>();
		result = depositService.fiatDeposit(depositDto);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false, SUCCESS, null, result);
	}

	/**
	 * Getting all pending fiat deposit request
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "v1/admin/pending/request")
	public Map<String, Object> getAllPendingReq() {
		List<FiatDeposit> pendingList = depositService.getAllPendingRequest();
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, pendingList);

	}

	/**
	 * Approval of the deposit request
	 * 
	 * @param approvalDto
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "v1/admin/approve/fiat/deposit")
	public Map<String, Object> approveFiatDepositRequest(@Valid @RequestBody ApprovalDto approvalDto) {
		Map<Object, Object> result = depositService.approveDeposit(approvalDto);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

/*	*//**
	 * Creating the crypto deposit request
	 * 
	 * @param cryptoDepositDto
	 * @return
	 *//*
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "v1/user/crypto/deposit")
	public Map<String, Object> createCryptoDeposit(@Valid @RequestBody CryptoDepositDto cryptoDepositDto) {
		Map<Object, Object> result = depositService.cryptoDeposit(cryptoDepositDto);
		return ResponseHandler.generateResponse(HttpStatus.CREATED, false, SUCCESS, null, result);

	}

	*//**
	 * Approve and update balance crypto deposit request
	 * 
	 * @param cryptoApprovalDto
	 * @return
	 *//*
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "v1/admin/approve/crypto/deposit")
	public Map<String, Object> approveCryptoDepositRequest(@Valid @RequestBody CryptoApprovalDto cryptoApprovalDto) {
		Map<Object, Object> result = depositService.approveCryptoRequest(cryptoApprovalDto);
		return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}*/
}
