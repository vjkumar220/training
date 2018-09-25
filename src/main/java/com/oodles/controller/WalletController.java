package com.oodles.controller;

import static com.oodles.util.Constants.SUCCESS;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.CryptoWalletDto;
import com.oodles.dto.FiatWalletDto;
import com.oodles.service.WalletService;
import com.oodles.util.ResponseHandler;

@RestController
public class WalletController {
	Logger logger = LoggerFactory.getLogger(WalletController.class);
	@Autowired
	private WalletService walletService;

	/**
	 * create crypto wallet
	 * 
	 * @param cryptoWallet
	 * @return
	 */
	@PostMapping(value = "v1/user/create/crypto/wallet")
	public Map<String, Object> createCryptoWallet(@RequestBody CryptoWalletDto cryptoWallet) {
		Map<String, Object> result = walletService.createCryptoWallet(cryptoWallet);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

	/**
	 * create fiat wallet
	 * 
	 * @param fiatWalletDto
	 * @return
	 */
	@PostMapping(value = "v1/user/create/fiat/wallet")
	public Map<String, Object> createFiatWallet(@RequestBody FiatWalletDto fiatWalletDto) {
		Map<String, Object> result = walletService.createFiatWallet(fiatWalletDto);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, SUCCESS, null, result);
	}

	/**
	 * Fait Wallet Deposit History
	 * 
	 * @param userId
	 * @return
	 */
/*	@GetMapping(value = "/fiatHistory/{userId}")
	public Map<String, Object> fiatHistory(@PathVariable Long userId) {
		Map<Object, Object> result = new HashMap<>();
		try {
			List<FiatDeposit> getRecords = walletService.fiatWalletHistory(userId);
			return ResponseHandler.generateResponse(HttpStatus.OK, false, "success", null, getRecords);
		} catch (ResourceNotFoundException e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, true, "error", null, result);
		}
	}*/

}