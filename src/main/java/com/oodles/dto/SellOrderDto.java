package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.oodles.enumeration.CryptoName;

public class SellOrderDto {
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CryptoName coinName; 
	@NotNull
	@Min(value=0  ,message = " Enter coinQuantity is greater than zero")
	private Double coinQuantity;
	@NotNull
	@Min(value=0  ,message = " Enter price is greater than zero")
	private Double sellDesiredPrice;
	@NotNull
	private Long userId;
	@NotNull
	private Long cryptoWalletId;
	
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
	public Long getCryptoWalletId() {
		return cryptoWalletId;
	}
	public void setCryptoWalletId(Long cryptoWalletId) {
		this.cryptoWalletId = cryptoWalletId;
	}
	public Double getSellDesiredPrice() {
		return sellDesiredPrice;
	}
	public void setSellDesiredPrice(Double sellDesiredPrice) {
		this.sellDesiredPrice = sellDesiredPrice;
	}


}
