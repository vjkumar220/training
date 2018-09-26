package com.oodles.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oodles.enums.DepositStatus;

public class CryptoApprovalDto {
	@NotNull
	private Long walletId;
	@NotBlank
	private DepositStatus status;
	@NotNull
	private Long transactionID;
	public Long getWalletId() {
		return walletId;
	}
	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}
	public DepositStatus getStatus() {
		return status;
	}
	public void setStatus(DepositStatus status) {
		this.status = status;
	}
	public Long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}
	
}
