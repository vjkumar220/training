package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oodles.enums.WalletType;

public class FiatDepositDto {
	@NotBlank
	@Enumerated(EnumType.STRING)
private WalletType walletType;
	@NotNull
private Double amount;
	@NotNull
private Long userId;


public WalletType getWalletType() {
		return walletType;
	}
	public void setWalletType(WalletType walletType) {
		this.walletType = walletType;
	}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}

}
