package com.student.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name="order_record")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderid;
    private String ordername;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    
    public Order(){
    }
    
    public Order(String ordername, User user){
    	this.ordername = ordername;
    	this.user = user;
    }

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}