package com.oodles.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.oodles.enumeration.OrderType;

public class OrderDto {
	@NotNull
	private String coinName; 
	@NotNull
	@Min(value=0  ,message = " Enter coinQuantity is greater than zero")
	private Double coinQuantity;
	@NotNull
	@Min(value=0  ,message = " Enter price is greater than zero")
	private Double price;
	@NotNull
	private Long userId;
	
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
