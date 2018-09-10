package com.oodles.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FiatDepositDto {
	//@NotNull
	//@Min(value=0  ,message = " Enter amount is greater than zero")
	private Double amount;
	//@NotNull
	private Long userId;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
//	private  String amount;
//	private String userId;
//	public String getAmount() {
//		return amount;
//	}
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	
	
}
