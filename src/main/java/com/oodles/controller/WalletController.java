package com.oodles.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.domain.CryptoWallet;
import com.oodles.exception.ResourceNotFoundException;
import com.oodles.service.WalletService;
import com.oodles.util.ResponseHandler;

@RestController
public class WalletController {

	@Autowired
	WalletService walletService;
	
	String result=null;

	@PostMapping(value ="/cryptowallet")
	public Map createCryptoWallet(@RequestBody CryptoWallet cryptoWallet) {
		try {
			result = walletService.createCryptoWallet(cryptoWallet);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}
}
