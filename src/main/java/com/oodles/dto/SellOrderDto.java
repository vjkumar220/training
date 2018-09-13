package com.oodles.dto;

import javax.validation.constraints.NotNull;

public class SellOrderDto {
	@NotNull
	private String coinName;
	@NotNull
	private Long coinQuantity;
	@NotNull
	  private Long userId;
	@NotNull
	  private Long desiredPrice;
	public Long getDesiredPrice() {
		return desiredPrice;
	}

	public void setDesiredPrice(Long desiredPrice) {
		this.desiredPrice = desiredPrice;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public Long getCoinQuantity() {
		return coinQuantity;
	}

	public void setCoinQuantity(Long coinQuantity) {
		this.coinQuantity = coinQuantity;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
