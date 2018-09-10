package com.oodles.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionID;
	private Long coinQuantity;
	 private String coinType;
	  @Enumerated
	    @Column
	  private TransactionStatus status;
	 private Long exchangeRate;
	 private Long netAmount;
	private double fees;
	 private Long grossAmount;
	public Long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}
	public Long getCoinQuantity() {
		return coinQuantity;
	}
	public void setCoinQuantity(Long coinQuantity) {
		this.coinQuantity = coinQuantity;
	}
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	public Long getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Long exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Long getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Long netAmount) {
		this.netAmount = netAmount;
	}
	
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public Long getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(Long grossAmount) {
		this.grossAmount = grossAmount;
	}


}
