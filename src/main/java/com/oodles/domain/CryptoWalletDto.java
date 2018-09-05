package com.oodles.domain;

public class CryptoWalletDto {
	private Long userId;
	private Long shadowBalance;
	private Long balance;
	private String coinName;
	private String walletType;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getShadowBalance() {
		return shadowBalance;
	}
	public void setShadowBalance(Long shadowBalance) {
		this.shadowBalance = shadowBalance;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public String getWalletType() {
		return walletType;
	}
	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}
}

