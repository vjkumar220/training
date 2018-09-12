package com.oodles.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.wallet.CryptoWallet;
import com.oodles.domain.wallet.FiatWallet;
import com.oodles.domain.withdraw.CryptoWithdraw;
import com.oodles.domain.withdraw.FiatWithdraw;
import com.oodles.dto.CryptoWithdrawDto;
import com.oodles.dto.FiatWithrawDto;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.CryptoWithdrawRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.FiatWithdrawRepository;

@Service
public class WithdrawService {

	Logger log = LoggerFactory.getLogger(WithdrawService.class);

	@Autowired
	private FiatWithdrawRepository fiatWithdrawRepository;
	@Autowired
	private FiatWalletRepository fiatWalletRepository;
	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	@Autowired
	private CryptoWithdrawRepository cryptoWithdrawRepository;

	private Map<Object, Object> result = new HashMap<>();

	// Withdraw the amount from fiat wallet

	public Map fiatWithdraw(FiatWithrawDto fiatWithdraw) {

		Double amount = fiatWithdraw.getAmount();
		Long walletId = fiatWithdraw.getWalletId();
		Optional<FiatWallet> findWallet = fiatWalletRepository.findById(walletId);
		if (findWallet.isPresent()) {
			FiatWallet fiatWallet = findWallet.get();
			Double fiatBalance = fiatWallet.getBalance();
			if (fiatBalance >= amount) {
				fiatWallet.setBalance(fiatBalance - amount);
				FiatWithdraw withdraw = new FiatWithdraw();
				withdraw.setAmount(amount);
				withdraw.setFiatWallet(fiatWallet);
				fiatWalletRepository.save(fiatWallet);
				fiatWithdrawRepository.save(withdraw);
				result.put("message", "Your amount withdraw is successfully done");
				return result;
			}
			result.put("errorMessage", "Balance is low for withdraw");
			return result;
		}
		result.put("errorMessage", "Wallet Not Found");
		return result;
	}

	// Withdraw coin from  crypto Wallet

	public Map cryptoWithdraw(CryptoWithdrawDto cryptoWithdrawDto) {
		Double coinQuantity = cryptoWithdrawDto.getCoinQuantity();
		Long walletId = cryptoWithdrawDto.getWalletId();
		String coinName = cryptoWithdrawDto.getCoinName();
		Optional<CryptoWallet> findCryptoWallet = cryptoWalletRepository.findById(walletId);
		if (findCryptoWallet.isPresent()) {
			CryptoWallet cryptoWallet = findCryptoWallet.get();
			Double cryptoBalance = cryptoWallet.getBalance();
			if (cryptoBalance >= coinQuantity) {
				cryptoWallet.setBalance(cryptoBalance - coinQuantity);
				CryptoWithdraw withdraw = new CryptoWithdraw();
				withdraw.setCoinCointain(coinQuantity);
				withdraw.setCoinName(coinName);
				withdraw.setCryptoWallet(cryptoWallet);
				cryptoWalletRepository.save(cryptoWallet);
				cryptoWithdrawRepository.save(withdraw);
				result.put("message", "Your amount withdraw is successfully done");
				return result;
			}
			result.put("errorMessage", "Crypto Wallet is not have sufficent balance");
			return result;
		}
		result.put("errorMessage", "Crypto Wallet is not found");
		return result;
	}

}
