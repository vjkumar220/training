package com.oodles.dto;

import javax.validation.constraints.NotNull;

import com.oodles.enumeration.CryptoName;

public class CryptoDepositDto {
	@NotNull
	private CryptoName coinName;
	@NotNull
	private Double coinQuantity;
	@NotNull
	private Long cryptoWalletId;
	@NotNull
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
