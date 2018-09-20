package com.oodles.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FiatWithdrawDto {
	@NotBlank
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
