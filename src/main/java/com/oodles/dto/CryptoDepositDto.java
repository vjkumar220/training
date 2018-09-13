package com.oodles.dto;

import com.oodles.enumeration.CryptoName;

public class CryptoDepositDto {
	
	private CryptoName coinName;
	
	private Double coinQuantity;
	
	private Long cryptoWalletId;

	public Double getCoinQuantity() {
		return coinQuantity;
	}

	public void setCoinQuantity(Double coinQuantity) {
		this.coinQuantity = coinQuantity;
	}

	public Long getCryptoWalletId() {
		return cryptoWalletId;
	}

	public void setCryptoWalletId(Long cryptoWalletId) {
		this.cryptoWalletId = cryptoWalletId;
	}

	public CryptoName getCoinName() {
		return coinName;
	}

	public void setCoinName(CryptoName coinName) {
		this.coinName = coinName;
	}
										
}
