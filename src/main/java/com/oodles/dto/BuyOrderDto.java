package com.oodles.dto;

import javax.validation.constraints.NotNull;

public class BuyOrderDto {
	@NotNull
	private String coinName;
	@NotNull
	private Double coinQuantity;
	@NotNull
	private Double desiredPrice;
	@NotNull
	private Long userId;

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getCoinQuantity() {
		return coinQuantity;
	}

	public void setCoinQuantity(Double coinQuantity) {
		this.coinQuantity = coinQuantity;
	}

	public Double getDesiredPrice() {
		return desiredPrice;
	}

	public void setDesiredPrice(Double desiredPrice) {
		this.desiredPrice = desiredPrice;
	}

	
}
