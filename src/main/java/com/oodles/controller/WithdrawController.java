package com.oodles.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.CryptoWithdrawDto;
import com.oodles.dto.FiatWithrawDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.WithdrawService;
import com.oodles.util.ResponseHandler;

@RestController
@RequestMapping("/withdraw")
public class WithdrawController {

	@Autowired
	private WithdrawService withdrawService;

	/**
	 * Withdraw from fiat wallet
	 * 
	 * @param fiatWithrawDto
	 * @return
	 */

	@PostMapping(value = "user/fiat/wallet")
	public Map<String, Object> fiatWithdraw(@Valid @RequestBody FiatWithrawDto fiatWithrawDto) {
		Map<Object, Object> result = withdrawService.fiatWithdraw(fiatWithrawDto);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
	}

	/**
	 * Withdraw from crypto Wallet
	 * 
	 * @param cryptoWithdrawDto
	 * @return
	 */

	@PostMapping(value = "user/crypto/wallet")
	public Map<String, Object> cryptoWithdraw(@Valid @RequestBody CryptoWithdrawDto cryptoWithdrawDto) {
		Map<Object, Object> result = withdrawService.cryptoWithdraw(cryptoWithdrawDto);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);

	}
}