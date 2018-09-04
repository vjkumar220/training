package com.oodles.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.CryptoWallet;
import com.oodles.models.FiatWallet;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatWalletRepository;

@Service
public class WalletService {
@Autowired
private FiatWalletRepository fiatWalletRepository;
private CryptoWalletRepository cryptoWalletRepository;

//create a Fiat wallet
public Map<String, Object>  createFiatWallet(FiatWallet fiatWallet) {
	Map<String, Object> result = new HashMap<String, Object>();
	String coinName=fiatWallet.getCoinName();
	String walletType=fiatWallet.getWalletType();
	Long balance=fiatWallet.getBalance();
	Long shadowBalance=fiatWallet.getShadowBalance();
	FiatWallet  id = fiatWalletRepository.findBywalletId(fiatWallet.getWalletId());
	if(id==null)
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
public Map<String, Object>  createCryptoWallet(CryptoWallet cryptoWallet) {
	Map<String, Object> result = new HashMap<String, Object>();
	String coinName=cryptoWallet.getCoinName();
	String walletType=cryptoWallet.getWalletType();
	Long balance=cryptoWallet.getBalance();
	Long shadowBalance=cryptoWallet.getShadowBalance();
	CryptoWallet  id = cryptoWalletRepository.findBywalletId(cryptoWallet.getWalletId());
	if(id==null)
	{
	CryptoWallet newCryptoWallet=new CryptoWallet();
	newCryptoWallet.setBalance(balance);
	newCryptoWallet.setCoinName(coinName);
	newCryptoWallet.setShadowBalance(shadowBalance);
	newCryptoWallet.setWalletType(walletType);
	cryptoWalletRepository.save(newCryptoWallet);
		result.put("responseMessage", "success");
		}
	return result;
}



}
