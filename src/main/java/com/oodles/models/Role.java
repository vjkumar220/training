package com.oodles.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "role")

public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    private String roleType;
    @ManyToMany(mappedBy="role", fetch = FetchType.EAGER)
    //@JsonBackReference
    @JsonBackReference(value="user-role")
    private Set<User> user;
    
    
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}
    


}