package com.oodles.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProfitBook {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long profitID;
	
	private Double ProfitAmount;
	private Long fees;
	private Long sellerId;
	private Long buyerId;
	public Long getProfitID() {
		return profitID;
	}
	public void setProfitID(Long profitID) {
		this.profitID = profitID;
	}
	public Double getProfitAmount() {
		return ProfitAmount;
	}
	public void setProfitAmount(Double profitAmount) {
		ProfitAmount = profitAmount;
	}
	public Long getFees() {
		return fees;
	}
	public void setFees(Long fees) {
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
	
	
	
}
