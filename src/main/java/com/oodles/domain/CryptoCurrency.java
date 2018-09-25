package com.oodles.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oodles.enumeration.CryptoName;

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
	@Enumerated(EnumType.STRING)
	private CryptoName coinName;
	@NotBlank
	private String symbol;
	@NotNull
	private Double fees;
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
