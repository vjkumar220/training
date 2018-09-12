package com.oodles.DTO;

public class SellOrderDTO {
	private String coinName;
	private Long coinQuantity;
	   
	  private Long userId;
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
