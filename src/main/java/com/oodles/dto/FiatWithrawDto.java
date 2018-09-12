package com.oodles.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FiatWithrawDto {
	@NotNull
	@Min(value = 0, message = " Enter amount is greater than zero")
	private Double amount;
	
	@NotNull
	private Long walletId;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}
	
	
	

}
