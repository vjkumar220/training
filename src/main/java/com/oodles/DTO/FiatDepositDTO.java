package com.oodles.DTO;

public class FiatDepositDTO {
private String walletType;
private Double amount;
private Long userId;
public String getWalletType() {
	return walletType;
}
public void setWalletType(String walletType) {
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
