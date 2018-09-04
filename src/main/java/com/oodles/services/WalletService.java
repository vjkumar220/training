package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.FiatWallet;
import com.oodles.models.User;
import com.oodles.models.UserWalletDTO;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.UserRepository;

@Service
public class WalletService {

	@Autowired
private FiatWalletRepository fiatWalletRepository;

	@Autowired
private CryptoWalletRepository cryptoWalletRepository;

	@Autowired
	private UserRepository userRepository;

//create a Fiat wallet
/*public Map<String, Object>  createFiatWallet(FiatWallet fiatWallet) {
	Map<String, Object> result = new HashMap<String, Object>();
	String coinName=fiatWallet.getCoinName();
	String walletType=fiatWallet.getWalletType();
	Long balance=fiatWallet.getBalance();
	Long shadowBalance=fiatWallet.getShadowBalance();
	User user_id=fiatWallet.getUser();
	Long userid=user_id.getId();
	FiatWallet  fwType = fiatWalletRepository.findBywalletType(fiatWallet.getWalletType());
	if(fwType.equals(walletType))
	{
	FiatWallet newFiatWallet=new FiatWallet();
		newFiatWallet.setBalance(balance);
		newFiatWallet.setCoinName(coinName);
		newFiatWallet.setShadowBalance(shadowBalance);
		newFiatWallet.setWalletType(walletType);
		fiatWalletRepository.save(newFiatWallet);
		result.put("responseMessage", "success");
		}
	return result;
}
//Create a Crypto wallet
public Map<String, Object> createCryptoWallet(CryptoWallet cryptoWallet) {
	Map<String, Object> result = new HashMap<String, Object>();
	String coinName=cryptoWallet.getCoinName();
	String walletType=cryptoWallet.getWalletType();
	Long balance=cryptoWallet.getBalance();
	Long shadowBalance=cryptoWallet.getShadowBalance();
	User user_id=cryptoWallet.getUser();
	Long userid=user_id.getId();
	CryptoWallet  wType = cryptoWalletRepository.findBywalletType(cryptoWallet.getWalletType());
	if(wType.equals(walletType))
	{
		User user=new User();
	CryptoWallet newCryptoWallet=new CryptoWallet();
	newCryptoWallet.setBalance(balance);
	newCryptoWallet.setCoinName(coinName);
	newCryptoWallet.setShadowBalance(shadowBalance);
	newCryptoWallet.setWalletType(walletType);
	//newCryptoWallet.
	cryptoWalletRepository.save(newCryptoWallet);
		result.put("responseMessage", "success");
		}
	return result;
}*/
//create a Fiat wallet
public Map<String, Object> createFiatWallet(UserWalletDTO userWalletDTO) {
	Map<String, Object> result = new HashMap<String, Object>();
	String coinName=userWalletDTO.getCoinName();
	String walletType=userWalletDTO.getWalletType();
	Long balance=userWalletDTO.getBalance();
	Long shadowBalance=userWalletDTO.getShadowBalance();
	Long user_id=userWalletDTO.getUser_id();
//logger
	FiatWallet  fwType = fiatWalletRepository.findBywalletType(userWalletDTO.getWalletType());
	if(fwType.equals(walletType))
	{
	FiatWallet newFiatWallet=new FiatWallet();
		newFiatWallet.setBalance(balance);
		newFiatWallet.setCoinName(coinName);
		newFiatWallet.setShadowBalance(shadowBalance);
		newFiatWallet.setWalletType(walletType);
		Optional<User>user=userRepository.findById(user_id);
		if(user.isPresent())
		{
		newFiatWallet.setUser(user.get());
		fiatWalletRepository.save(newFiatWallet);
		result.put("responseMessage", "success");
		}
	
}return result;
}

}


