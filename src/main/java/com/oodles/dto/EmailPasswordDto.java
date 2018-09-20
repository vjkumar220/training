package com.oodles.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmailPasswordDto {
	@NotNull
	  @Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid Mail")
		private String email;
	@NotNull
		private String passToken;
	@NotNull
		private Long expirytime;
		@NotNull(message = "Enter the password")
		@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&].{8,}$", message = "The password should have contain minimum 1 lowercase, maximum 1 uppercase, 1 digit and 1 special character(Minimum Length=8) ")
		private String password;
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassToken() {
			return passToken;
		}
		public void setPassToken(String passToken) {
			this.passToken = passToken;
		}
		public Long getExpirytime() {
			return expirytime;
		}
		public void setExpirytime(Long expirytime) {
			this.expirytime = expirytime;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

}
