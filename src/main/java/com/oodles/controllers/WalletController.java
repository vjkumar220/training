package com.oodles.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oodles.models.CryptoWallet;
import com.oodles.models.FiatWallet;
import com.oodles.services.WalletService;

@Controller
public class WalletController {
@Autowired
private WalletService walletService;
//Create a fiat wallet
@RequestMapping(method = RequestMethod.POST, value = "/v1/fiatwallet")

public Map createUser(@Valid @RequestBody FiatWallet fiatWallet)  {
	Map result=null;
	try{
		 result=walletService.createFiatWallet(fiatWallet);
		 return com.oodles.exceptions.ResourceNotFound.generateResponse(HttpStatus.OK, false, "success", null, result);
	}
	catch(com.oodles.exceptions.UserNotFoundException exception){
		return com.oodles.exceptions.ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
	}		
}
//Create a crypto wallet
@RequestMapping(method = RequestMethod.POST, value = "/v1/cryptowallet")

public Map createUser(@Valid @RequestBody CryptoWallet cryptoWallet)  {
	Map result=null;
	try{
		 result=walletService.createCryptoWallet(cryptoWallet);
		 return com.oodles.exceptions.ResourceNotFound.generateResponse(HttpStatus.OK, false, "success", null, result);
	}
	catch(com.oodles.exceptions.UserNotFoundException exception){
		return com.oodles.exceptions.ResourceNotFound.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
	}		
}
}
