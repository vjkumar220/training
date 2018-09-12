package com.oodles.controller;

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
@RequestMapping("/v1")
public class WithdrawController {

	@Autowired
	private WithdrawService withdrawService;

	private Map result = null;

	@PostMapping(value = "/fiatWithdraw")
	public Map fiatWithdraw(@Valid @RequestBody FiatWithrawDto fiatWithrawDto) {

		try {
			result = withdrawService.fiatWithdraw(fiatWithrawDto);
			if (result.containsKey("message")) {
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
			}
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
	
	@PostMapping(value = "cryptoWithdraw")
	public Map cryptoWithdraw(@Valid @RequestBody CryptoWithdrawDto cryptoWithdrawDto) {
		
		try {
			result = withdrawService.cryptoWithdraw(cryptoWithdrawDto);
			if (result.containsKey("message")) {
				return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			} else {
				return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
			}
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}

}
