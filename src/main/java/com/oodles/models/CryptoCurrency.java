package com.oodles.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;

@Entity
public class CryptoCurrency {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long currencyId;
    
    @NotNull
	private String 	coinName;
    
    @NotNull
	private String symbol;
    
    @NotNull
	private Long fees;
    
    @NotNull
private Long initialSupply;
    
    @NotNull
	private Long price;
	public Long getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Long getFees() {
		return fees;
	}
	public void setFees(Long fees) {
		this.fees = fees;
	}
	public Long getInitialSupply() {
		return initialSupply;
	}
	public void setInitialSupply(Long initialSupply) {
		this.initialSupply = initialSupply;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
}
