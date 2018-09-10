package com.oodles.DTO;

public class FiatDepositDTO {
private String walletType;
private Long amount;
private Long userId;
public String getWalletType() {
	return walletType;
}
public void setWalletType(String walletType) {
	this.walletType = walletType;
}
public Long getAmount() {
	return amount;
}
public void setAmount(Long amount) {
	this.amount = amount;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}

}
