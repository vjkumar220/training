package com.oodles.dto;

import javax.validation.constraints.NotNull;

import com.oodles.enumeration.DepositStatus;

public class ApprovalDto {
	@NotNull(message = "Enter user ID")
	private Long userId;
	@NotNull(message = "Enter Status")
	private DepositStatus depositStatus;
	@NotNull(message = "Enter the order Id")
	private Long depositId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public DepositStatus getDepositStatus() {
		return depositStatus;
	}
	public void setDepositStatus(DepositStatus depositStatus) {
		this.depositStatus = depositStatus;
	}
	public Long getDepositId() {
		return depositId;
	}
	public void setDepositId(Long depositId) {
		this.depositId = depositId;
	}
	
}
