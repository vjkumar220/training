package com.oodles.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class VerifyEmailDto {
	@NotBlank
	  @Pattern(regexp="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message="Invalid Mail")
	private String email;
	@NotBlank
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


}
