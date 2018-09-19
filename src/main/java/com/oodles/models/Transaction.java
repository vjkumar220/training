package com.oodles.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oodles.enums.TransactionStatus;
@Entity

public class Transaction {
	
			@Id
		    @GeneratedValue(strategy = GenerationType.SEQUENCE)
			private Long transactionID;
			private Long coinQuantity;
			 private String coinType;
			  @Enumerated
			    @Column
			  private TransactionStatus status;
			 private Long exchangeRateAsDesiredPrice;
			 private Long netAmount;
			private double fees;
			 private Long grossAmount;
			 private Long buyerId;
			 private Long sellerId;
			 @ManyToOne(fetch = FetchType.LAZY)
			    @JoinColumn(name = "sellerorderid", nullable = false)
			 @JsonBackReference(value="selltransaction-sellorder")
			    private SellOrder sellOrder;
			
			 @ManyToOne(fetch = FetchType.LAZY)
			    @JoinColumn(name = "buyerorderid", nullable = false)
			 @JsonBackReference(value="selltransaction-buyorder")
			    private BuyOrder buyOrder;

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

			public Long getExchangeRateAsDesiredPrice() {
				return exchangeRateAsDesiredPrice;
			}

			public void setExchangeRateAsDesiredPrice(Long exchangeRateAsDesiredPrice) {
				this.exchangeRateAsDesiredPrice = exchangeRateAsDesiredPrice;
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
		
}
