package com.oodles.dto;

import javax.validation.constraints.NotNull;

import com.oodles.enumeration.DepositStatus;

public class CryptoApprovalDto {

	@NotNull
	private DepositStatus depositStatus;
	
	@NotNull
	private Long walletId;
	
	@NotNull
	private Long depositId;

	public DepositStatus getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(DepositStatus depositStatus) {
		this.depositStatus = depositStatus;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Long getDepositId() {
		return depositId;
	}

	public void setDepositId(Long depositId) {
		this.depositId = depositId;
	}
	
}
