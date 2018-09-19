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
public class SellTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long sellTransactionId;

	private Double coinQuantity;
	private String coinName;
	private String transactionStatus;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date transactionCreatedOn;
	private Double netAmount;
	private Double transationFee;
	private Double exchangeRateSellDesiredPrice;
	private Double grossAmount;
	private Long sellerId;
	private Long buyerId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyOrder_id")
	@JsonBackReference(value = "for Orders hdfsd")
	private BuyOrder buyOrder;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sellOrder_id")
	@JsonBackReference(value = "for Orders dfwef")
	private SellOrder sellOrder;
	
	

	public SellTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getSellTransactionId() {
		return sellTransactionId;
	}

	public void setSellTransactionId(Long sellTransactionId) {
		this.sellTransactionId = sellTransactionId;
	}

	public Double getCoinQuantity() {
		return coinQuantity;
	}

	public void setCoinQuantity(Double coinQuantity) {
		this.coinQuantity = coinQuantity;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
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

	public Double getExchangeRateSellDesiredPrice() {
		return exchangeRateSellDesiredPrice;
	}

	public void setExchangeRateSellDesiredPrice(Double exchangeRateSellDesiredPrice) {
		this.exchangeRateSellDesiredPrice = exchangeRateSellDesiredPrice;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public BuyOrder getBuyOrder() {
		return buyOrder;
	}

	public void setBuyOrder(BuyOrder buyOrder) {
		this.buyOrder = buyOrder;
	}

	public SellOrder getSellOrder() {
		return sellOrder;
	}

	public void setSellOrder(SellOrder sellOrder) {
		this.sellOrder = sellOrder;
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

	public SellTransaction(Double coinQuantity, String coinName, String transactionStatus, Double netAmount,
			Double transationFee, Double exchangeRateSellDesiredPrice, Double grossAmount, Long sellerId, Long buyerId,
			BuyOrder buyOrder, SellOrder sellOrder) {
		super();
		this.coinQuantity = coinQuantity;
		this.coinName = coinName;
		this.transactionStatus = transactionStatus;
		this.netAmount = netAmount;
		this.transationFee = transationFee;
		this.exchangeRateSellDesiredPrice = exchangeRateSellDesiredPrice;
		this.grossAmount = grossAmount;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.buyOrder = buyOrder;
		this.sellOrder = sellOrder;
	}
	
	
	
}
