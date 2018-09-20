
package com.oodles.models;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "user")

public class User  {
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
   private String enabled;
   private String otp;
   private String confirmationToken;
   private Long expirytime;
   private String passToken;
  
   @OneToMany(cascade = CascadeType.ALL,
           fetch = FetchType.EAGER,
           mappedBy = "user")
  // @JsonManagedReference
 //  @JsonBackReference(value="user-cryptowallet")
   private Set<CryptoWallet> cryptowallet = new HashSet<>();
   
   @OneToOne(fetch = FetchType.EAGER,
           cascade =  CascadeType.ALL,
           mappedBy = "user")
   //@JsonManagedReference
   //@JsonBackReference(value="user-fiatwallet")
   
   private FiatWallet fiatwallet;
   
   @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
   @JoinTable(name="User_Role", 
               joinColumns={@JoinColumn(name="id")}, 
               inverseJoinColumns={@JoinColumn(name="roleId")})
  // @JsonManagedReference
  // @JsonBackReference(value="user-role")
   
   private Set<Role> role;

 
   // mapping with  order
   @OneToMany(cascade = CascadeType.ALL,
           fetch = FetchType.EAGER,
           mappedBy = "user")
   private Set<LimitOrder> order = new HashSet<>();
//mapping with fiat deposit
   @OneToMany(cascade = CascadeType.ALL,
           fetch = FetchType.EAGER,
           mappedBy = "user")
   private Set<FiatDeposit> fiatdeposit = new HashSet<>();
  //mapping with Buy Order
   @OneToMany(cascade = CascadeType.ALL,
           fetch = FetchType.EAGER,
           mappedBy = "user")
   private Set<BuyOrder> buyorder = new HashSet<>();
   //mapping with Sell Order
   @OneToMany(cascade = CascadeType.ALL,
           fetch = FetchType.EAGER,
           mappedBy = "user")
   private Set<SellOrder> sellorder = new HashSet<>();
  
   public User() {
       super();
       
       
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


public String getEnabled() {
	return enabled;
}

public void setEnabled(String enabled) {
	this.enabled = enabled;
}

public String getOtp() {
	return otp;
}

public void setOtp(String otp) {
	this.otp = otp;
}

public String getConfirmationToken() {
	return confirmationToken;
}

public void setConfirmationToken(String confirmationToken) {
	this.confirmationToken = confirmationToken;
}

public Long getExpirytime() {
	return expirytime;
}

public void setExpirytime(Long expirytime) {
	this.expirytime = expirytime;
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

public Set<LimitOrder> getOrder() {
	return order;
}

public void setOrder(Set<LimitOrder> order) {
	this.order = order;
}

public Set<FiatDeposit> getFiatdeposit() {
	return fiatdeposit;
}

public void setFiatdeposit(Set<FiatDeposit> fiatdeposit) {
	this.fiatdeposit = fiatdeposit;
}

public String getPassToken() {
	return passToken;
}

public void setPassToken(String passToken) {
	this.passToken = passToken;
}

public Set<BuyOrder> getBuyorder() {
	return buyorder;
}

public void setBuyorder(Set<BuyOrder> buyorder) {
	this.buyorder = buyorder;
}

public Set<SellOrder> getSellorder() {
	return sellorder;
}

public void setSellorder(Set<SellOrder> sellorder) {
	this.sellorder = sellorder;
}



	
}
