package com.oodles.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VerifyEmailDto {
	@NotNull
	  @Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid Mail")
	private String email;
	private Long expirytime;
	private String confirmationToken;

public String getConfirmationToken() {
	return confirmationToken;
}

public void setConfirmationToken(String confirmationToken) {
	this.confirmationToken = confirmationToken;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getExpirytime() {
	return expirytime;
}

public void setExpirytime(Long expirytime) {
	this.expirytime = expirytime;
}

}
