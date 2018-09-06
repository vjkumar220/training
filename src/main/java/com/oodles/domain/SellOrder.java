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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class SellOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sellerId;
	private Double sellAmount;
	private Double sellFee;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sellOrderCreatedOn;
	private String sellStaus = "pending";
	private String coinName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "for Buy Order")
	private User user;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getSellAmount() {
		return sellAmount;
	}

	public void setSellAmount(Double sellAmount) {
		this.sellAmount = sellAmount;
	}

	public Double getSellFee() {
		return sellFee;
	}

	public void setSellFee(Double sellFee) {
		this.sellFee = sellFee;
	}

	public Date getSellOrderCreatedOn() {
		return sellOrderCreatedOn;
	}

	public void setSellOrderCreatedOn(Date sellOrderCreatedOn) {
		this.sellOrderCreatedOn = sellOrderCreatedOn;
	}

	public String getSellStaus() {
		return sellStaus;
	}

	public void setSellStaus(String sellStaus) {
		this.sellStaus = sellStaus;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

}
