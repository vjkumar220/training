package com.oodles.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
/**
 * is not clear
 * @author shivam
 *
 */
public class CryptoCurrency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long currencyId;
	@NotNull
	private String coinName;
	@NotNull
	private String symbol;
	@NotNull
	private String fees;
	@NotNull
	private String initialSupply;
	@NotNull
	private String price;

	// Setter Getter
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

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getInitialSupply() {
		return initialSupply;
	}

	public void setInitialSupply(String initialSupply) {
		this.initialSupply = initialSupply;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
