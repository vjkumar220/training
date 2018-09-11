package com.oodles.DTO;

public class FiatWalletDTO {
	private Long balance;
    private String coinName;
 private Long shadowBalance;
private Long user_id;
private String walletType;
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
public Long getShadowBalance() {
	return shadowBalance;
}
public void setShadowBalance(Long shadowBalance) {
	this.shadowBalance = shadowBalance;
}
public Long getUser_id() {
	return user_id;
}
public void setUser_id(Long user_id) {
	this.user_id = user_id;
}
public String getWalletType() {
	return walletType;
}
public void setWalletType(String walletType) {
	this.walletType = walletType;
}

}
