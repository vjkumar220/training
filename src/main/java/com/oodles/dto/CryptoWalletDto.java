package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.oodles.enumeration.CryptoName;

public class CryptoWalletDto {
	@NotNull
	private Long userId;
	@NotNull
	@Enumerated(EnumType.STRING)
	private CryptoName coinName;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public CryptoName getCoinName() {
		return coinName;
	}
	public void setCoinName(CryptoName coinName) {
		this.coinName = coinName;
	}

}

