package com.oodles.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.CryptoWalletDto;
import com.oodles.dto.FiatWalletDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.WalletService;
import com.oodles.util.ResponseHandler;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	Logger logger = LoggerFactory.getLogger(WalletController.class);
	@Autowired
	private WalletService walletService;

	private Map result = null;

	@PostMapping(value = "/create-crypto-wallet")
	public Map createCryptoWallet(@RequestBody CryptoWalletDto  cryptoWallet) {
		logger.info("in create waller");
		try {
			logger.info("In create wallet try");
			result = walletService.createCryptoWallet(cryptoWallet);
			logger.info("Getting result",result);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
	
	@PostMapping(value = "/create-fiat-wallet")
	public Map createFiatWallet(@RequestBody FiatWalletDto fiatWalletDto) {
		logger.info("in create waller");
		try {
			logger.info("In create wallet try");
			result = walletService.createFiatWallet(fiatWalletDto);
			logger.info("Getting result",result); 
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
	
	//Fait Wallet Depost History
	@GetMapping(value = "/fiatHistory/{userId}")
	public List fiatHistory(@PathVariable Long userId){
		List getRecords = walletService.fiatWalletHistory(userId);
		return getRecords;
	}

}