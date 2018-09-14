package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.oodles.enumeration.CryptoName;

public class BuyOrderDto {
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CryptoName coinName; 
	@NotNull
	@Min(value=0  ,message = " Enter coinQuantity is greater than zero")
	private Double coinQuantity;
	@NotNull
	@Min(value=0  ,message = " Enter price is greater than zero")
	private Double buyDesiredPrice;
	@NotNull
	private Long userId;
	@NotNull
	private Long fiatWalletId;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getFiatWalletId() {
		return fiatWalletId;
	}
	public void setFiatWalletId(Long fiatWalletId) {
		this.fiatWalletId = fiatWalletId;
	}
	public Double getBuyDesiredPrice() {
		return buyDesiredPrice;
	}
	public void setBuyDesiredPrice(Double buyDesiredPrice) {
		this.buyDesiredPrice = buyDesiredPrice;
	}
	

}
