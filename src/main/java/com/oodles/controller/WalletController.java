package com.oodles.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.CryptoWallet;
import com.oodles.domain.CryptoWalletDto;
import com.oodles.domain.FiatWalletDto;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.WalletService;
import com.oodles.util.ResponseHandler;

@RestController
public class WalletController {
	Logger logger = LoggerFactory.getLogger(WalletController.class);
	@Autowired
	private WalletService walletService;

	private Map result = null;

	@PostMapping(value = "/cryptowallet")
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
	
	@PostMapping(value = "/fiatowallet")
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

}