package com.oodles.dto;

import javax.validation.constraints.NotNull;

import com.oodles.enums.OrderType;

public class OrderDto {
	@NotNull
	private OrderType orderType; 
	@NotNull
	private Long desiredPrice;
	@NotNull
	private String coinName;
	@NotNull
	private Long coinQuantity;
	@NotNull
	  private Long userId;
	  
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	
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
	
	  
}
