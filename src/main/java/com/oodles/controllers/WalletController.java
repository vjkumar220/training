package com.oodles.controllers;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.models.UserWalletDTO;
import com.oodles.services.WalletService;

@Controller
public class WalletController {
	private Logger logger=LoggerFactory.getLogger(WalletController.class);
@Autowired
private WalletService walletService;
//Create a fiat wallet
@RequestMapping(method = RequestMethod.POST, value = "/v1/fiatwallet")

public Map createFiatWallet(@RequestBody UserWalletDTO userWalletDTO)  {
	Map result=null;
	try{
		logger.info("Entered in create fiatwallet");
		logger.info("userWalletDTO ="+userWalletDTO);
		 result=walletService.createFiatWallet(userWalletDTO);
		 logger.info("created fiatwallet");
		 return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			
			
	}
	catch(ResourceNotFoundException exception){
		//logger.info("Enter in creat
		
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null, result);
	}		
}
//Create a crypto wallet
@RequestMapping(method = RequestMethod.POST, value = "/v1/cryptowallet")

public Map createCryptoWallet(@RequestBody UserWalletDTO userWalletDTO)  {
	Map result=null;
	try{
		 result=walletService.createCryptoWallet(userWalletDTO);
		 return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
	}
	catch(ResourceNotFoundException exception){
		return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
	}		
}
}
