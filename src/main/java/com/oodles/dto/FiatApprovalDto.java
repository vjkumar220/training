package com.oodles.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.oodles.enums.DepositStatus;

public class FiatApprovalDto {
	@NotNull
		private Long userId;
	@NotBlank
	private DepositStatus status;
	@NotNull
	private Long transactionID;
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
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

