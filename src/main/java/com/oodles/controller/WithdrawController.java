package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.FiatWithrawDto;
import com.oodles.service.WithdrawService;
import com.oodles.util.ResponseHandler;


@RestController
public class WithdrawController {

	@Autowired
	private WithdrawService withdrawService;

	/**
	 * Withdraw from fiat wallet
	 * 
	 * @param fiatWithrawDto
	 * @return
	 */
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "v1/user/withdraw/fiat/wallet")
	public Map<String, Object> fiatWithdraw(@Valid @RequestBody FiatWithrawDto fiatWithrawDto) {
		Map<Object, Object> result = withdrawService.fiatWithdraw(fiatWithrawDto);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

}
