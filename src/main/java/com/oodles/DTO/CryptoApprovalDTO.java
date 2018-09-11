package com.oodles.DTO;

import com.oodles.models.DepositStatus;

public class CryptoApprovalDTO {
	private Long walletId;
	private DepositStatus status;
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
