
package com.oodles.models;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "user")
//@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, 
        allowGetters = true)
public class User implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 	@Size(min=2, message="Name should have atleast 2 characters")
    private String name;
	 @NotNull
	  @Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid Mail")
       private String email;
	  @NotNull
	 @Pattern(regexp="^[6-9]\\d{9}$",message="Invalid Mobile Number")
    private String mobilenumber;
    @NotNull
	  @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message="The password should have contain minimum 1 lowercase, maximum 1 uppercase, 1 digit and 1 special character(Minimum Length=8)")
    
    private String password;
   @NotNull
    private String country;	

   @OneToMany(cascade = CascadeType.ALL,
           fetch = FetchType.EAGER,
           mappedBy = "user")
   @JsonManagedReference
   private Set<CryptoWallet> cryptowallet = new HashSet<>();
   
   @OneToOne(fetch = FetchType.EAGER,
           cascade =  CascadeType.ALL,
           mappedBy = "user")
   @JsonManagedReference
   private FiatWallet fiatwallet;
   
   @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
   @JoinTable(name="User_Role", 
               joinColumns={@JoinColumn(name="id")}, 
               inverseJoinColumns={@JoinColumn(name="roleId")})
   @JsonManagedReference
   private Set<Role> role;


   public User() {

   } 
public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	public Set<CryptoWallet> getCryptowallet() {
		return cryptowallet;
	}
	public void setCryptowallet(Set<CryptoWallet> cryptowallet) {
		this.cryptowallet = cryptowallet;
	}
	public FiatWallet getFiatwallet() {
		return fiatwallet;
	}
	public void setFiatwallet(FiatWallet fiatwallet) {
		this.fiatwallet = fiatwallet;
	}
	public Set<Role> getRole() {
		return role;
	}
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	
}
