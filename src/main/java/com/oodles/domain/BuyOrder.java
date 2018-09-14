package com.oodles.domain;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oodles.enumeration.CryptoName;
import com.oodles.enumeration.OrderStatus;
@Entity
public class BuyOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long buyOrderId;
	@Enumerated(EnumType.STRING)
	private OrderStatus buyOrderStatus;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date buyOrderCreatedOn;
	@NotNull
	private Double buyPrice;
	@NotNull
	private String buyCoinName;
	@NotNull
	private Double buyCoinQuantity;
	@NotNull
	private Double orderPrice;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "for Orders")
	private User user;
	
	public BuyOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getBuyOrderId() {
		return buyOrderId;
	}
	public void setBuyOrderId(Long buyOrderId) {
		this.buyOrderId = buyOrderId;
	}
	public OrderStatus getBuyOrderStatus() {
		return buyOrderStatus;
	}
	public void setBuyOrderStatus(OrderStatus buyOrderStatus) {
		this.buyOrderStatus = buyOrderStatus;
	}
	public Date getBuyOrderCreatedOn() {
		return buyOrderCreatedOn;
	}
	public void setBuyOrderCreatedOn(Date buyOrderCreatedOn) {
		this.buyOrderCreatedOn = buyOrderCreatedOn;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Double getBuyCoinQuantity() {
		return buyCoinQuantity;
	}
	public void setBuyCoinQuantity(Double buyCoinQuantity) {
		this.buyCoinQuantity = buyCoinQuantity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getBuyCoinName() {
		return buyCoinName;
	}
	public void setBuyCoinName(String buyCoinName) {
		this.buyCoinName = buyCoinName;
	}
	public BuyOrder(OrderStatus buyOrderStatus, @NotNull Double buyPrice, @NotNull String buyCoinName,
			@NotNull Double buyCoinQuantity, @NotNull Double orderPrice, User user) {
		super();
		this.buyOrderStatus = buyOrderStatus;
		this.buyPrice = buyPrice;
		this.buyCoinName = buyCoinName;
		this.buyCoinQuantity = buyCoinQuantity;
		this.orderPrice = orderPrice;
		this.user = user;
	}
	
	
	
	
}
