package com.oodles.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Userdetail")
public class User  implements Serializable {
 // private static final long serialVersionUID = -3465813074586302847L;
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	 
	    @Column
	    private String name;
	 
	    @Column
	    private String email;
	 
	    @Column
	    private String mobile;
	 
	    @Column
	    private String password;
	    
	    @Column
	    private String confirmpassword;
	    
	    @Column
	    private String country;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmpassword() {
			return confirmpassword;
		}

		public void setConfirmpassword(String confirmpassword) {
			this.confirmpassword = confirmpassword;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
	    
}
