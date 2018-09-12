package com.oodles.controllers;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.DTO.CryptoApprovalDTO;
import com.oodles.DTO.CryptoDepositDTO;
import com.oodles.DTO.FiatApprovalDTO;
import com.oodles.DTO.FiatDepositDTO;
import com.oodles.DTO.FiatWalletDTO;
import com.oodles.DTO.UserWalletDTO;
import com.oodles.exceptions.ResourceNotFoundException;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.services.WalletService;

@RestController
public class WalletController {
	private Logger logger=LoggerFactory.getLogger(WalletController.class);
@Autowired
private WalletService walletService;
//Create a fiat wallet
@RequestMapping(method = RequestMethod.POST, value = "/v1/fiatwallet")

public Map createFiatWallet(@RequestBody FiatWalletDTO userWalletDTO)  {
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

//Deposit in fiat wallet

@RequestMapping(method = RequestMethod.POST, value = "/v1/fiatdeposit")

public Map createFiatDeposit(@RequestBody FiatDepositDTO fiatDepositDTO)  {
	Map result=null;
	try{
		logger.info("Entered in create fiatwallet");
		logger.info("userWalletDTO ="+fiatDepositDTO);
		 result=walletService.createFiatDeposit(fiatDepositDTO);
		 logger.info("created fiatwallet");
		 return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			
			
	}
	catch(ResourceNotFoundException exception){	
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null, result);
	}		
}
//Admin Approval for Fiat wallet
@RequestMapping(method = RequestMethod.POST, value = "/v1/fiatdepositapproval")

public Map fiatDepositApproval(@RequestBody FiatApprovalDTO fiatApprovalDTO)  {
	logger.info("Approval controller entered");
	Map result=null;
	try{
		
		 result=walletService.fiatDepositApproval(fiatApprovalDTO);
		 logger.info("Approval controller end");
		 return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			
			
	}
	catch(ResourceNotFoundException exception){	
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null, result);
	}		
}
//Deposit in Crypto wallet


@RequestMapping(method = RequestMethod.POST, value = "/v1/cryptodeposit")

public Map createCryptoDeposit(@RequestBody CryptoDepositDTO cryptoDepositDTO)  {
	Map result=null;
	try{
		
		 result=walletService.createCryptoDeposit(cryptoDepositDTO);
		
		 return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			
			
	}
	catch(ResourceNotFoundException exception){	
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null, result);
	}		
}
//Admin Approval for Crypto Deposit

@RequestMapping(method = RequestMethod.POST, value = "/v1/cryptodepositapproval")

public Map CryptoDepositApproval(@RequestBody CryptoApprovalDTO cryptoApprovalDTO)  {
	logger.info("Approval controller entered");
	Map result=null;
	try{
		
		 result=walletService.CryptoDepositApproval(cryptoApprovalDTO);
		 logger.info("Approval controller end");
		 return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, result);
			
			
	}
	catch(ResourceNotFoundException exception){	
		return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "error", null, result);
	}		
}

}
