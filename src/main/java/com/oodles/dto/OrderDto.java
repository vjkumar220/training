package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.oodles.enumeration.CryptoName;
import com.oodles.enumeration.OrderType;

public class OrderDto {
	@NotNull
	@Enumerated(EnumType.STRING)
	private CryptoName coinName; 
	@NotNull
	@Min(value=0  ,message = " Enter coinQuantity is greater than zero")
	private Double coinQuantity;
	@NotNull
	@Min(value=0  ,message = " Enter price is greater than zero")
	private Double price;
	@NotNull
	private Long userId;
	@NotNull
	private Long walletId;
	

	public CryptoName getCoinName() {
		return coinName;
	}
	public void setCoinName(CryptoName coinName) {
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
	public Long getWalletId() {
		return walletId;
	}
	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}
	
	
}
