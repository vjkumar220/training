package com.oodles.dto;

import javax.validation.constraints.NotNull;

public class RoleDto {
	@NotNull
private Long userId;
	@NotNull
private Long roleId;


public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public Long getRoleId() {
	return roleId;
}
public void setRoleId(Long roleId) {
	this.roleId = roleId;
}

}
