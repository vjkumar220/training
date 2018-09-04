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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	@NotNull
	@Email(message = "Enter Valid Email Id")
	private String email;
	@NotNull
	@NotBlank(message = "Enter Password")
	// in this regexp checking atleast one upper and lower cases, number, special
	// char
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&].{8,}$", message = " Enter Password Valid password")
	private String Password;
	// In this regexp we checking phone number length 10 and number starting with
	// 6-9
	@NotNull
	@Pattern(regexp = "^[6-9]\\d{9}$")
	private String phoneNumber;
	@NotNull
	@Size(min = 2, message = "Enter the valid country name")
	private String country;
	@NotNull
	private String status = "inactive";

	@OneToOne(mappedBy = "user")
	@JsonBackReference(value = "for fiat wallet")
	private FiatWallet fiatWallet;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

	private Set<CryptoWallet> cryptoWallets;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
	private Set<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CurrencyOrder> currencyOrders;

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

	public Set<CurrencyOrder> getCurrencyOrders() {
		return currencyOrders;
	}

	public void setCurrencyOrders(Set<CurrencyOrder> currencyOrders) {
		this.currencyOrders = currencyOrders;
	}


}
