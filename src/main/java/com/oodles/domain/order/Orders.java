package com.oodles.domain.order;

import java.io.Serializable;
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
import com.oodles.domain.user.User;
import com.oodles.enumeration.OrderStatus;
import com.oodles.enumeration.OrderType;

@Entity
public class Orders implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	@Enumerated(EnumType.STRING)
	private OrderType orderType;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date orderCreatedOn;
	@NotNull
	private Double price;
	@NotNull
	private String coinName;
	@NotNull
	private Double coinQuantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "for Orders")
	private User user;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderCreatedOn() {
		return orderCreatedOn;
	}

	public void setOrderCreatedOn(Date orderCreatedOn) {
		this.orderCreatedOn = orderCreatedOn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getCoinQuantity() {
		return coinQuantity;
	}

	public void setCoinQuantity(Double coinQuantity) {
		this.coinQuantity = coinQuantity;
	}

}
