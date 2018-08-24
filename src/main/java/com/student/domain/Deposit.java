package com.student.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Deposit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	private String orderName;

	private String orderValue;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderAt;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "transaction_record", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "orderId"), inverseJoinColumns = @JoinColumn(name = "withdraw_id", referencedColumnName = "withdrawId"))
	private Set<Withdraw> withdraw;

	public Deposit() {

	}

	public Deposit(String orderName, String orderValue) {
		this.orderName = orderName;
		this.orderValue = orderValue;
	}

	public <Withdarw> Deposit(String orderName, String orderValue, Set<Withdraw> withdraw) {
		this.orderName = orderName;
		this.orderName = orderValue;
		this.withdraw = withdraw;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}

	public Date getOrderAt() {
		return orderAt;
	}

	public void setOrderAt(Date orderAt) {
		this.orderAt = orderAt;
	}

}
