package com.oodles.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.CryptoWallet;
import com.oodles.domain.User;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatCurrencyRepository;

@Service
public class WalletService {
	@Autowired
	CryptoWalletRepository cryptoWalletRepository;

	@Autowired
	FiatCurrencyRepository fiatCurrencyRepository;

	// Creating crypto Wallet
	public String createCryptoWallet(CryptoWallet cryptoWallet) {
		String coinName = cryptoWallet.getCoinName();
		String walletType = cryptoWallet.getWalletType();
		User user = cryptoWallet.getUser();
		Long userId= user.getId();
		CryptoWallet newCoinName = cryptoWalletRepository.findByCoinName(coinName);
		Optional<CryptoWallet> newUserId = cryptoWalletRepository.findById(userId);
		CryptoWallet newWalletType = cryptoWalletRepository.findByWalletType(walletType);
		if (newCoinName == null && newWalletType == null) {
			CryptoWallet wallet = new CryptoWallet();
			wallet.setCoinName(coinName);
			wallet.setWalletType(walletType);
			wallet.setUser(user);
			cryptoWalletRepository.save(wallet);
			return "YOUR WALLET IS ADDED...";
		}
		return "Your wallet is not added";
	}

}
