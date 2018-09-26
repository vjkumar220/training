package com.oodles.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import com.oodles.enums.OrderType;

public class MarketOrderDto {
	@NotBlank
	private OrderType orderType; 
	@NotBlank
	
	private String coinName;
	@NotNull
	@Positive
	private Long coinQuantity;
	@NotNull
	  private Long userId;

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
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
