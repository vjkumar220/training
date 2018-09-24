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
public class SellTransaction {
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		private Long selltransactionID;
		private Double coinQuantity;
		 private String coinType;
		  @Enumerated
		    @Column
		  private TransactionStatus status;
		 private Double exchangeRateAsDesiredPrice;
		 private Double netAmount;
		private Long fees;
		 private Double grossAmount;
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

		public Long getSelltransactionID() {
			return selltransactionID;
		}

		public void setSelltransactionID(Long selltransactionID) {
			this.selltransactionID = selltransactionID;
		}

		public Double getCoinQuantity() {
			return coinQuantity;
		}

		public void setCoinQuantity(Double coinQuantity) {
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

		public Double getExchangeRateAsDesiredPrice() {
			return exchangeRateAsDesiredPrice;
		}

		public void setExchangeRateAsDesiredPrice(Double exchangeRateAsDesiredPrice) {
			this.exchangeRateAsDesiredPrice = exchangeRateAsDesiredPrice;
		}

		public Double getNetAmount() {
			return netAmount;
		}

		public void setNetAmount(Double netAmount) {
			this.netAmount = netAmount;
		}

		

		public Long getFees() {
			return fees;
		}

		public void setFees(Long fees) {
			this.fees = fees;
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
	
		
	}
