package com.oodles.DTO;

import com.oodles.models.DepositStatus;

public class FiatApprovalDTO {
	
		private Long userId;
		private DepositStatus status;
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
