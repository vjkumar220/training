package com.oodles.dto;

import javax.validation.constraints.NotNull;

public class FiatWalletDto {
	@NotNull
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

}
