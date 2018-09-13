package com.oodles.dto;

import javax.validation.constraints.NotNull;

public class CryptoWithdrawDto {
	@NotNull
private Double NumberOfCoin;
	@NotNull
private Long walletId;
public Double getNumberOfCoin() {
	return NumberOfCoin;
}
public void setNumberOfCoin(Double numberOfCoin) {
	NumberOfCoin = numberOfCoin;
}
public Long getWalletId() {
	return walletId;
}
public void setWalletId(Long walletId) {
	this.walletId = walletId;
}

}
