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
		private Long coinQuantity;
		 private String coinType;
		  @Enumerated
		    @Column
		  private TransactionStatus status;
		 private Long exchangeRateAsDesiredPrice;
		 private Long netAmount;
		private double fees;
		 private Long grossAmount;
		 
		 @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name = "sellerorderid", nullable = false)
		 @JsonBackReference(value="selltransaction-sellorder")
		    private SellOrder sellOrder;
		
		 @ManyToOne(fetch = FetchType.LAZY)
		    @JoinColumn(name = "buyerorderid", nullable = false)
		 @JsonBackReference(value="selltransaction-buyorder")
		    private BuyOrder buyOrder;
	
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
		
		public Long getSelltransactionID() {
			return selltransactionID;
		}
		public void setSelltransactionID(Long selltransactionID) {
			this.selltransactionID = selltransactionID;
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
