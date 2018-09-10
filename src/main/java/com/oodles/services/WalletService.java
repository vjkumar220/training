package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.DTO.FiatApprovalDTO;
import com.oodles.DTO.FiatDepositDTO;
import com.oodles.DTO.UserWalletDTO;
import com.oodles.models.CryptoWallet;
import com.oodles.models.DepositStatus;
import com.oodles.models.FiatDeposit;
import com.oodles.models.FiatWallet;
import com.oodles.models.User;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatDepositRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.UserRepository;

@Service
public class WalletService {
 private Logger logger=LoggerFactory.getLogger(WalletService.class);
	@Autowired
private FiatWalletRepository fiatWalletRepository;

	@Autowired
private CryptoWalletRepository cryptoWalletRepository;

	@Autowired
	private UserRepository userRepository;
@Autowired
private FiatDepositRepository fiatDepositRepository;

//create a Fiat wallet
	public Map<String, Object> createFiatWallet(UserWalletDTO userWalletDTO) {
		logger.info("createFiatwallet entered");
		Map<String, Object> result = new HashMap<String, Object>();
		String coinName=userWalletDTO.getCoinName();
		String walletType=userWalletDTO.getWalletType();
		logger.info(" walletType in service ="+walletType);
		Long balance=userWalletDTO.getBalance();
		Long shadowBalance=userWalletDTO.getShadowBalance();
		Long userId=userWalletDTO.getUser_id();
	//logger
		Optional<User>user=userRepository.findById(userId);
		
		if(user.isPresent())
		{
		
			User foundUser=user.get();
			
		FiatWallet  fwType = fiatWalletRepository.findByWalletTypeAndUser(walletType,foundUser);

		if(fwType==null)
		//if(fwType.equals(walletType))
		{
		FiatWallet newFiatWallet=new FiatWallet();
			newFiatWallet.setBalance(balance);
			newFiatWallet.setCoinName(coinName);
			newFiatWallet.setShadowBalance(shadowBalance);
			newFiatWallet.setWalletType(walletType);
			
			newFiatWallet.setUser(foundUser);
			fiatWalletRepository.save(newFiatWallet);
			result.put("responseMessage", "success");
			}
		
	}
		return result;
	}
	
	//Create Crypto Wallet	
	
	public Map<String, Object> createCryptoWallet(UserWalletDTO userWalletDTO) {
		logger.info("createFiatwallet entered");
		Map<String, Object> result = new HashMap<String, Object>();
		String coinName=userWalletDTO.getCoinName();
		String walletType=userWalletDTO.getWalletType();
		logger.info(" walletType in service ="+walletType);
		Long balance=userWalletDTO.getBalance();
		Long shadowBalance=userWalletDTO.getShadowBalance();
		Long userId=userWalletDTO.getUser_id();
	//logger
		Optional<User>user=userRepository.findById(userId);
		
		if(user.isPresent())
		{
		
			User foundUser=user.get();
			
		CryptoWallet  fwType = cryptoWalletRepository.findByWalletTypeAndUser(walletType,foundUser);

		if(!(fwType==null))
		//if(fwType.equals(walletType))
		{
			CryptoWallet newCryptoWallet=new CryptoWallet();
			newCryptoWallet.setBalance(balance);
			newCryptoWallet.setCoinName(coinName);
			newCryptoWallet.setShadowBalance(shadowBalance);
			newCryptoWallet.setWalletType(walletType);
			
			newCryptoWallet.setUser(foundUser);
			cryptoWalletRepository.save(newCryptoWallet);
			result.put("responseMessage", "success");
			}
		
	}
		return result;
	}
/*	// Fiat wallet Deposit
	public User fiatDeposit(Long userid,Long amount,String walletType)
	{
		Optional<User> value=userRepository.findById(userid);
		 User user = value.get();
		 if(value.isPresent())
		{
			 FiatWallet  fwType = fiatWalletRepository.findByWalletTypeAndUser(walletType,user);

				if(fwType==null)
				//if(fwType.equals(walletType))
				{
			 FiatWallet newFiatWallet=new FiatWallet();
			 
			 Long currentBalance=newFiatWallet.getBalance();
			 Long updatedBalance=currentBalance+amount;
			newFiatWallet.setBalance(updatedBalance);
			 fiatWalletRepository.save(newFiatWallet);
		}	
		
	}
		 return user;
}*/
//fiat Wallet Deposit
	
	public Map<String, Object> createFiatDeposit(FiatDepositDTO fiatDepositDTO) {
		logger.info("create deposit request");
		Map<String, Object> result = new HashMap<String, Object>();
		String walletType=fiatDepositDTO.getWalletType();
		Long amount=fiatDepositDTO.getAmount();
		Long userId=fiatDepositDTO.getUserId();
		//logger
			Optional<User>user=userRepository.findById(userId);
			
			if(user.isPresent())
			{
			
				User foundUser=user.get();
				
			FiatDeposit  fwType = fiatDepositRepository.findByWalletTypeAndUser(walletType,foundUser);
			if(fwType==null)
			{
				FiatDeposit newFiatDeposit=new FiatDeposit();
				newFiatDeposit.setAmount(amount);
				newFiatDeposit.setWalletType(walletType);
				DepositStatus status= newFiatDeposit.getStatus();
				newFiatDeposit.setStatus(status.PENDING);
				newFiatDeposit.setUser(foundUser);
				fiatDepositRepository.save(newFiatDeposit);
				result.put("responseMessage", "success");
				return result;
			}
			
	}
			result.put("responseMessage", "user id donot exist");	
		return result;
	
	}	
	
	
	//Admin Approval 
	
	public Map<String, Object>fiatDepositApproval(FiatApprovalDTO fiatApprovalDTO)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		String newstatus= fiatApprovalDTO.getStatus().toString();
		Long userId=fiatApprovalDTO.getUserId();
	
	Optional<FiatDeposit>user=fiatDepositRepository.findById(userId);
	
	
	if(user.isPresent()) 
	{
		FiatDeposit foundUser=user.get();
		if(newstatus.equalsIgnoreCase("APPROVED"))
		{
		FiatDeposit newfiatdeposit=new FiatDeposit();
		Long amount=newfiatdeposit.getAmount();
		 FiatWallet newFiatWallet=new FiatWallet();
		 Long currentBalance=newFiatWallet.getBalance();
		 
		 Long updatedBalance=currentBalance+amount;
		newFiatWallet.setBalance(updatedBalance);
		 fiatWalletRepository.save(newFiatWallet);
		
	}	
}
	return result;
}
}