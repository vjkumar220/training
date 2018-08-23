package com.oodles.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Wallet")
public class Wallet {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
	    private Integer id;
	    private Integer Amount ;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "id", referencedColumnName = "id")
	    private User user;
	    public Wallet() {
	    }
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getAmount() {
			return Amount;
		}
		public void setAmount(Integer amount) {
			Amount = amount;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
	    
}
