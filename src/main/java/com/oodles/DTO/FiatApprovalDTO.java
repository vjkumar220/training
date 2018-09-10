package com.oodles.DTO;

import com.oodles.models.DepositStatus;

public class FiatApprovalDTO {
	
		private Long userId;
		private DepositStatus status;
		
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
		

		}

