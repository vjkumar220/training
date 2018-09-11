package com.oodles.dto;

public class CryptoDepositDto {
	
	private String coinName;
	
	private Double coinQuantity;
	
	private Long cryptoWalletId;

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

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
										
}
