package com.oodles.dto;

import javax.validation.constraints.NotNull;

public class FiatCurrencyDto {
	@NotNull
	private String 	coinName;
	@NotNull
	private String symbol;
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
	
	
}
