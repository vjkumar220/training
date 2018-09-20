package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.oodles.enumeration.CryptoName;

public class CryptoCurrencyDto {
	@NotNull
	@Enumerated(EnumType.STRING)
	private CryptoName coinName;
	@NotNull
	private String symbol;
	@NotNull
	private Double fees;
	@NotNull
	private Double initialSupply;
	@NotNull
	private Double price;
	public CryptoName getCoinName() {
		return coinName;
	}
	public void setCoinName(CryptoName coinName) {
		this.coinName = coinName;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	public Double getInitialSupply() {
		return initialSupply;
	}
	public void setInitialSupply(Double initialSupply) {
		this.initialSupply = initialSupply;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
