package com.oodles.DTO;

public class BuyOrderDTO {

	private String coinName;
	private Long coinQuantity;
	private Long desiredPrice;
	  private Long userId;

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

	public Long getDesiredPrice() {
		return desiredPrice;
	}

	public void setDesiredPrice(Long desiredPrice) {
		this.desiredPrice = desiredPrice;
	}
	  
}
