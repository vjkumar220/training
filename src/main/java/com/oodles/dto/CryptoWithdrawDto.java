package com.oodles.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CryptoWithdrawDto {
	@NotNull
	@Positive
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
