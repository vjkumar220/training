package com.oodles.domain;

import java.io.Serializable;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.util.concurrent.SettableListenableFuture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull(message = "Enter the name")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	@NotNull(message = "Enter the email")
	@Email(message = "Enter Valid Email Id")
	private String email;

	// in this regexp checking atleast one upper and lower cases, number, special
	// char
	@NotNull(message = "Enter the password")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&].{8,}$", message = " Enter Password Valid password")
	private String Password;

	// In this regexp we checking phone number length 10 and number starting with
	// 6-9
	@NotNull(message = "Enter the phone number")
	@Pattern(regexp = "^[6-9]\\d{9}$")
	private String phoneNumber;
	@NotNull(message = "Enter the country")
	@Size(min = 2, message = "Enter the valid country name")
	private String country;

	private String status = "inactive";

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private FiatWallet fiatWallet;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CryptoWallet> cryptoWallets;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BuyOrder> buyOrder;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SellOrder> sellOrder;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FiatDeposit> fiatDeposit;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
	private Set<Role> roles;

	private String mobileCode;
	
	private String emailCode;
	
	private String passToken;

	
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

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FiatWallet getFiatWallet() {
		return fiatWallet;
	}

	public void setFiatWallet(FiatWallet fiatWallet) {
		this.fiatWallet = fiatWallet;
	}

	public Set<CryptoWallet> getCryptoWallets() {
		return cryptoWallets;
	}

	public void setCryptoWallets(Set<CryptoWallet> cryptoWallets) {
		this.cryptoWallets = cryptoWallets;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	public Set<FiatDeposit> getFiatDeposit() {
		return fiatDeposit;
	}

	public void setFiatDeposit(Set<FiatDeposit> fiatDeposit) {
		this.fiatDeposit = fiatDeposit;
	}

	public String getPassToken() {
		return passToken;
	}

	public void setPassToken(String passToken) {
		this.passToken = passToken;
	}

	public Set<BuyOrder> getBuyOrder() {
		return buyOrder;
	}

	public void setBuyOrder(Set<BuyOrder> buyOrder) {
		this.buyOrder = buyOrder;
	}

	public Set<SellOrder> getSellOrder() {
		return sellOrder;
	}

	public void setSellOrder(Set<SellOrder> sellOrder) {
		this.sellOrder = sellOrder;
	}
	
	
}
