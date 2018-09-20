package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oodles.enums.CoinName;
import com.oodles.enums.WalletType;

public class FiatWalletDto {
	@NotNull
	private Double balance;
	@NotBlank
	 @Enumerated(EnumType.STRING)
	
	private CoinName coinName;
	@NotNull
 private Double shadowBalance;
	@NotNull
private Long user_id;
	@NotBlank
	@Enumerated(EnumType.STRING)
private WalletType walletType;
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}

public CoinName getCoinName() {
	return coinName;
}
public void setCoinName(CoinName coinName) {
	this.coinName = coinName;
}
public Double getShadowBalance() {
	return shadowBalance;
}
public void setShadowBalance(Double shadowBalance) {
	this.shadowBalance = shadowBalance;
}
public Long getUser_id() {
	return user_id;
}
public void setUser_id(Long user_id) {
	this.user_id = user_id;
}
public WalletType getWalletType() {
	return walletType;
}
public void setWalletType(WalletType walletType) {
	this.walletType = walletType;
}



}
