package com.oodles.service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class ProfitTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long profitId;
	
	@NotNull
	private Double profitAmount;
	
	@NotNull
	private Double fees;
	
	@NotNull
	private Long sellerId;
	
	@NotNull
	private Long buyerId;
	
	@NotNull
	private Double totalProfit;

	public Long getProfitId() {
		return profitId;
	}

	public void setProfitId(Long profitId) {
		this.profitId = profitId;
	}

	public Double getProfitAmount() {
		return profitAmount;
	}

	public void setProfitAmount(Double profitAmount) {
		this.profitAmount = profitAmount;
	}

	public Double getFees() {
		return fees;
	}

	public void setFees(Double fees) {
		this.fees = fees;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}
	
	
}
