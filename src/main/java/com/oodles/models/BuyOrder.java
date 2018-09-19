package com.oodles.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oodles.enums.OrderStatus;

@Entity
public class BuyOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    
	private Long buyOrderId; 
	private Double buyDesiredPrice;
	private Double coinQuantity;
	private String coinName;
	  @Enumerated(EnumType.STRING)
	    
	    private  OrderStatus status;
	private Double remainingCoin;
	  @Column(name="timeStamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	   @Temporal(TemporalType.TIMESTAMP)
	private Date orderCreatedOn;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	 @JsonBackReference(value="user-buyorder")
	    private User user;
//mapping with  transaction
	  @OneToMany(cascade = CascadeType.ALL,
	           fetch = FetchType.EAGER,
	           mappedBy = "buyOrder")
	   private Set<Transaction> transaction = new HashSet<>();
	  @OneToMany(cascade = CascadeType.ALL,
	           fetch = FetchType.EAGER,
	           mappedBy = "buyOrder")
	   private Set<BuyTransaction> buyTransaction = new HashSet<>();
	  @OneToMany(cascade = CascadeType.ALL,
	           fetch = FetchType.EAGER,
	           mappedBy = "sellOrder")
	   private Set<SellTransaction> sellTransaction = new HashSet<>();
	public Long getBuyOrderId() {
		return buyOrderId;
	}

	public void setBuyOrderId(Long buyOrderId) {
		this.buyOrderId = buyOrderId;
	}

	public Double getBuyDesiredPrice() {
		return buyDesiredPrice;
	}

	public void setBuyDesiredPrice(Double buyDesiredPrice) {
		this.buyDesiredPrice = buyDesiredPrice;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Date getOrderCreatedOn() {
		return orderCreatedOn;
	}

	public void setOrderCreatedOn(Date orderCreatedOn) {
		this.orderCreatedOn = orderCreatedOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getRemainingCoin() {
		return remainingCoin;
	}

	public void setRemainingCoin(Double remainingCoin) {
		this.remainingCoin = remainingCoin;
	}

	public Set<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}

	public Set<BuyTransaction> getBuyTransaction() {
		return buyTransaction;
	}

	public void setBuyTransaction(Set<BuyTransaction> buyTransaction) {
		this.buyTransaction = buyTransaction;
	}

	public Set<SellTransaction> getSellTransaction() {
		return sellTransaction;
	}

	public void setSellTransaction(Set<SellTransaction> sellTransaction) {
		this.sellTransaction = sellTransaction;
	}




}
