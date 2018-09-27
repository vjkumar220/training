package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.oodles.enums.CryptoCoin;
import com.oodles.enums.CyptoWalletType;

public class UserWalletDto {
	@NotNull
	@Positive
	private Double balance;
	@NotBlank
	 @Enumerated(EnumType.STRING)
    private CryptoCoin coinName;
	@NotNull
	@Positive
 private Double shadowBalance;
	@NotNull
private Long user_id;
	@NotBlank
	@Enumerated(EnumType.STRING)
private CyptoWalletType walletType;
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}

public CryptoCoin getCoinName() {
	return coinName;
}
public void setCoinName(CryptoCoin coinName) {
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
public CyptoWalletType getWalletType() {
	return walletType;
}
public void setWalletType(CyptoWalletType walletType) {
	this.walletType = walletType;
}

}