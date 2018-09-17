package com.oodles.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.CryptoWallet;
import com.oodles.domain.CryptoWithdraw;
import com.oodles.domain.FiatWallet;
import com.oodles.domain.FiatWithdraw;
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

	/**
	 * Withdraw the amount from fiat wallet
	 * 
	 * @param fiatWithdraw
	 * @return
	 */

	public Map fiatWithdraw(FiatWithrawDto fiatWithdraw) {
		Map<Object, Object> result = new HashMap<>();
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
				result.put("responseMessage", "Your amount withdraw is successfully done");
				return result;
			}
			result.put("responseMessage", "Balance is low for withdraw");
			return result;
		}
		result.put("responseMessage", "Wallet Not Found");
		return result;
	}

	// Withdraw coin from crypto Wallet

	public Map<Object, Object> cryptoWithdraw(CryptoWithdrawDto cryptoWithdrawDto) {
		Map<Object, Object> result = new HashMap<>();
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
				result.put("responseMessage", "Your amount withdraw is successfully done");
			}
			result.put("responseMessage", "Crypto Wallet is not have sufficent balance");
		}
		result.put("responseMessage", "Crypto Wallet is not found");
		return result;
	}

}
