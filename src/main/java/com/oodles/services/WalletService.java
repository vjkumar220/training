package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.CryptoWallet;
import com.oodles.models.FiatWallet;
import com.oodles.models.User;
import com.oodles.models.UserWalletDTO;
import com.oodles.repository.CryptoWalletRepository;
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
		
	}return result;
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
		
	}return result;
	}
}

