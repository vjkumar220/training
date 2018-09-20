package com.oodles.dto;

import javax.validation.constraints.NotNull;

public class CryptoCurrencyDto {
	 
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
