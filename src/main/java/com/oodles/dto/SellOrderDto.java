package com.oodles.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class SellOrderDto {
	@NotBlank
	private String coinName;
	@NotNull
	@Positive
	private Double coinQuantity;
	@NotNull
	  private Long userId;
	@NotNull
	@Positive
	  private Double desiredPrice;
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public Double getCoinQuantity() {
		return coinQuantity;
	}
	public void setCoinQuantity(Double coinQuantity) {
		this.coinQuantity = coinQuantity;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getDesiredPrice() {
		return desiredPrice;
	}
	public void setDesiredPrice(Double desiredPrice) {
		this.desiredPrice = desiredPrice;
	}
	
}
