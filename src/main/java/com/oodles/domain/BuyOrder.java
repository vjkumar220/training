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
public class BuyOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long buyerId;
	private Double buyAmount;
	private Double buyFee;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date buyOrderCreatedOn;
	private String buyStaus = "pending";
	private String coinName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "for Buy Order")
	private User user;

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Double getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(Double buyAmount) {
		this.buyAmount = buyAmount;
	}

	public Double getBuyFee() {
		return buyFee;
	}

	public void setBuyFee(Double buyFee) {
		this.buyFee = buyFee;
	}

	public Date getBuyOrderCreatedOn() {
		return buyOrderCreatedOn;
	}

	public void setBuyOrderCreatedOn(Date buyOrderCreatedOn) {
		this.buyOrderCreatedOn = buyOrderCreatedOn;
	}

	public String getBuyStaus() {
		return buyStaus;
	}

	public void setBuyStaus(String buyStaus) {
		this.buyStaus = buyStaus;
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

}
