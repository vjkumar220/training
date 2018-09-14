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
import com.oodles.enumeration.OrderStatus;
@Entity
public class SellOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sellOrderId;
	@Enumerated(EnumType.STRING)
	private OrderStatus sellOrderStatus;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date sellOrderCreatedOn;
	@NotNull
	private Double sellPrice;
	@NotNull
	private String sellCoinName;
	@NotNull
	private Double sellCoinQuantity;
	@NotNull
	private Double orderPrice;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "for sell helllo Orders")
	private User user;

	public Long getSellOrderId() {
		return sellOrderId;
	}

	public void setSellOrderId(Long sellOrderId) {
		this.sellOrderId = sellOrderId;
	}

	public OrderStatus getSellOrderStatus() {
		return sellOrderStatus;
	}

	public void setSellOrderStatus(OrderStatus sellOrderStatus) {
		this.sellOrderStatus = sellOrderStatus;
	}

	public Date getSellOrderCreatedOn() {
		return sellOrderCreatedOn;
	}

	public void setSellOrderCreatedOn(Date sellOrderCreatedOn) {
		this.sellOrderCreatedOn = sellOrderCreatedOn;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getSellCoinName() {
		return sellCoinName;
	}

	public void setSellCoinName(String sellCoinName) {
		this.sellCoinName = sellCoinName;
	}

	public Double getSellCoinQuantity() {
		return sellCoinQuantity;
	}

	public void setSellCoinQuantity(Double sellCoinQuantity) {
		this.sellCoinQuantity = sellCoinQuantity;
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

	public SellOrder(OrderStatus sellOrderStatus, @NotNull Double sellPrice, @NotNull String sellCoinName,
			@NotNull Double sellCoinQuantity, @NotNull Double orderPrice, User user) {
		super();
		this.sellOrderStatus = sellOrderStatus;
		this.sellPrice = sellPrice;
		this.sellCoinName = sellCoinName;
		this.sellCoinQuantity = sellCoinQuantity;
		this.orderPrice = orderPrice;
		this.user = user;
	}

	public SellOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
