package com.oodles.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.CryptoWallet;
import com.oodles.models.FiatDeposit;
import com.oodles.models.FiatWallet;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatDepositRepository;
import com.oodles.repository.FiatWalletRepository;

@Service
public class WallethHistoryService {
	@Autowired
	private FiatWalletRepository fiatWalletRepository;
	@Autowired
	private FiatDepositRepository fiatDepositRepository;
	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	
	public List<FiatDeposit> fiatWalletDepositHistory(Long userId) {
		return fiatDepositRepository.findByUserId(userId);
	}
	public FiatWallet fiatWalletHistory(Long userId) {
		return fiatWalletRepository.findByUserId(userId);
	}
	public List<CryptoWallet> cryptoWalletHistory(Long userId) {
		return cryptoWalletRepository.findByUserId(userId);
	}
	
	
}
