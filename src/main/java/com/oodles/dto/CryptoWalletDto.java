package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.oodles.enums.CryptoCoin;

public class CryptoWalletDto {
	@NotNull
	private Long userId;
	@NotNull
	@Enumerated(EnumType.STRING)
	private CryptoCoin coinName;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public CryptoCoin getCoinName() {
		return coinName;
	}
	public void setCoinName(CryptoCoin coinName) {
		this.coinName = coinName;
	}
	
}
