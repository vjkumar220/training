package com.oodles.DTO;

import com.oodles.models.OrderType;

public class OrderDTO {
	
	private OrderType orderType; 
	private Long desiredPrice;
	
	private String coinName;
	private Long coinQuantity;
	    
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
