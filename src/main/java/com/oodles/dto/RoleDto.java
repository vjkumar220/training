package com.oodles.dto;

import javax.validation.constraints.NotNull;

public class RoleDto {
	@NotNull
	private String roleType;

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	

}
