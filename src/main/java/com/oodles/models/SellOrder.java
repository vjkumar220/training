package com.oodles.models;

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
public class SellOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
	private Long sellOrderId;
	private Long amount;
	private Long fee;
	private String coinName;
	private String status;
	  @Column(name="timeStamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	   @Temporal(TemporalType.TIMESTAMP)
	private Date orderCreatedOn;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	 //@JsonBackReference
	 @JsonBackReference(value="user-sellorder")
	    private User user;
	  
	  
	  
	  
	public Long getSellOrderId() {
		return sellOrderId;
	}
	public void setSellOrderId(Long sellOrderId) {
		this.sellOrderId = sellOrderId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getOrderCreatedOn() {
		return orderCreatedOn;
	}
	public void setOrderCreatedOn(Date orderCreatedOn) {
		this.orderCreatedOn = orderCreatedOn;
	}
	
	
}
