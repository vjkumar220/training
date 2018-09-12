package com.oodles.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CryptoWithdrawDto {

	@NotNull
	@Min(value = 0, message = " Enter amount is greater than zero")
	private Double coinQuantity;
	
	@NotNull
	private  String coinName;
	
	@NotNull
	private Long walletId;

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Double getCoinQuantity() {
		return coinQuantity;
	}

	public void setCoinQuantity(Double coinQuantity) {
		this.coinQuantity = coinQuantity;
	}
	
}
