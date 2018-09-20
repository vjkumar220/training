package com.oodles.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class CryptoCurrencyDto {
	 
	@NotBlank
	private String 	coinName;
    
	@NotBlank
	private String symbol;
    
	@NotNull
	@Positive
	private Long fees;
    
	@NotNull
	@PositiveOrZero
private Long initialSupply;
    
	@NotNull
	@PositiveOrZero
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
