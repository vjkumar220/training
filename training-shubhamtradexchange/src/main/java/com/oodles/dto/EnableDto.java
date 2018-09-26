package com.oodles.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.oodles.enums.ActivationStatus;
import com.oodles.enums.CryptoCoin;

public class EnableDto {
private Long UserId;
@NotBlank
@Enumerated(EnumType.STRING)
private ActivationStatus Status;
public Long getUserId() {
	return UserId;
}
public void setUserId(Long userId) {
	UserId = userId;
}
public ActivationStatus getStatus() {
	return Status;
}
public void setStatus(ActivationStatus status) {
	Status = status;
}

}
