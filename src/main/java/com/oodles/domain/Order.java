package com.oodles.domain;



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
@Table(name="orderdetail")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderid;
    private String ordername;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Users users;
    
    public Order(){
    }
    
    public Order(String ordername, Users users){
    	this.ordername = ordername;
    	this.users = users;
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

	public Users getUser() {
		return users;
	}

	public void setUser(Users users) {
		this.users = users;
	}
    
    
}