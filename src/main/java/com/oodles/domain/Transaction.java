package com.oodles.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long transactionId;

	private Double coinQuantity;
	private String cointype;
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date transactionCreatedOn;
	private Double netAmount;
	private Double transationFee;
	private Double exchangeRate;
	private Double grossAmount;
	private Long buyerId;
	private Long sellerId;
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyOrder_id")
	@JsonBackReference(value = "for Orders")
	private BuyOrder buyOrder;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sellOrder_id")
	@JsonBackReference(value = "for Orderssddas ")
	private SellOrder sellOrder;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Double getCoinQuantity() {
		return coinQuantity;
	}
	public void setCoinQuantity(Double coinQuantity) {
		this.coinQuantity = coinQuantity;
	}
	public String getCointype() {
		return cointype;
	}
	public void setCointype(String cointype) {
		this.cointype = cointype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTransactionCreatedOn() {
		return transactionCreatedOn;
	}
	public void setTransactionCreatedOn(Date transactionCreatedOn) {
		this.transactionCreatedOn = transactionCreatedOn;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	public Double getTransationFee() {
		return transationFee;
	}
	public void setTransationFee(Double transationFee) {
		this.transationFee = transationFee;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public Double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
