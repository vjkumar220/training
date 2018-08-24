package com.oodles.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "Walletdetail")
public class Wallet {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	   
	    private Integer walletid;
	    private Integer Amount ;

	    @OneToOne(mappedBy = "wallet")
	  
	    private Users users;
	    public Wallet() {
	    }
		
		public Integer getWalletid() {
			return walletid;
		}

		public void setWalletid(Integer walletid) {
			this.walletid = walletid;
		}

		public Integer getAmount() {
			return Amount;
		}

		public void setAmount(Integer amount) {
			Amount = amount;
		}

		public Users getUser() {
			return users;
		}
		public void setUser(Users users) {
			this.users = users;
		}
	    
}
