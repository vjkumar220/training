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
public class BuyTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long buyTransactionId;

	private Double coinQuantity;
	private String cointype;
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date 	transactionCreatedOn;
	private Double netAmount;
	private Double transationFee;
	private Double exchangeRate;
	private Double grossAmount;
	private Long sellerId;
	private Long buyerId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sellOrder_id")
	@JsonBackReference(value = "for Orders")
	private SellOrder sellOrder;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyOrder_id")
	@JsonBackReference(value = "for Orders")
	private BuyOrder buyOrder;

	

	public BuyTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getBuyTransactionId() {
		return buyTransactionId;
	}


	public void setBuyTransactionId(Long buyTransactionId) {
		this.buyTransactionId = buyTransactionId;
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


	public SellOrder getSellOrder() {
		return sellOrder;
	}


	public void setSellOrder(SellOrder sellOrder) {
		this.sellOrder = sellOrder;
	}


	public BuyOrder getBuyOrder() {
		return buyOrder;
	}


	public void setBuyOrder(BuyOrder buyOrder) {
		this.buyOrder = buyOrder;
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


	public BuyTransaction(Double coinQuantity, String cointype, String status, Double netAmount, Double transationFee,
			Double exchangeRate, Double grossAmount, Long sellerId, Long buyerId, SellOrder sellOrder,
			BuyOrder buyOrder) {
		super();
		this.coinQuantity = coinQuantity;
		this.cointype = cointype;
		this.status = status;
		this.netAmount = netAmount;
		this.transationFee = transationFee;
		this.exchangeRate = exchangeRate;
		this.grossAmount = grossAmount;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.sellOrder = sellOrder;
		this.buyOrder = buyOrder;
	}
	
	
}
