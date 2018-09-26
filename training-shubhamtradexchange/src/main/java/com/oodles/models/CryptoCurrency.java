package com.oodles.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class CryptoCurrency {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long currencyId;
    
    @NotNull
	private String 	coinName;
    
    @NotNull
	private String symbol;
    
    @NotNull
	private Long fees;
    
    @NotNull
private Double initialSupply;
    
    @NotNull
	private Double price;
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
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getInitialSupply() {
		return initialSupply;
	}
	public void setInitialSupply(Double initialSupply) {
		this.initialSupply = initialSupply;
	}
	
}
