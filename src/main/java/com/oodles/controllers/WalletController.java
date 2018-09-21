package com.oodles.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oodles.dto.CryptoApprovalDto;
import com.oodles.dto.CryptoDepositDto;
import com.oodles.dto.CryptoWalletDto;
import com.oodles.dto.CryptoWithdrawDto;
import com.oodles.dto.FiatApprovalDto;
import com.oodles.dto.FiatDepositDto;
import com.oodles.dto.FiatWithdrawDto;
import com.oodles.dto.StringConstant;
import com.oodles.exceptions.ResponseHandler;
import com.oodles.services.WalletService;

@RestController
public class WalletController {
	private Logger logger = LoggerFactory.getLogger(WalletController.class);
	@Autowired
	private WalletService walletService;

	/**
	 *  Create a fiat wallet
	 * @param userWalletDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/wallets/fiat/{userId}")

	public Map createFiatWallet(@PathVariable Long userId) {
		Map result = null;
		
			
			result = walletService.createFiatWallet(userId);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

	}

	/**
	 *  Create a crypto wallet
	 * @param userWalletDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/wallets/crypto")

	public Map createCryptoWallet(@RequestBody CryptoWalletDto cryptoWalletDTO) {
		Map result = null;
		
			result = walletService.createCryptoWallet(cryptoWalletDTO);
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);
		
	}

	/**
	 *  Deposit in fiat wallet
	 * @param fiatDepositDTO
	 * @return
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/wallets/fiat/deposit")

	public Map createFiatDeposit(@RequestBody FiatDepositDto fiatDepositDTO) {
		Map result = null;
		
			logger.info("Entered in create fiatwallet");
			logger.info("userWalletDTO =" + fiatDepositDTO);
			result = walletService.createFiatDeposit(fiatDepositDTO);
			logger.info("created fiatwallet");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

	}

	/**
	 *  Admin Approval for Fiat wallet
	 * @param fiatApprovalDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/v1/admin/wallets/fiat/deposit/approval")

	public Map fiatDepositApproval(@RequestBody FiatApprovalDto fiatApprovalDTO) {
		logger.info("Approval controller entered");
		Map result = null;
		
			result = walletService.fiatDepositApproval(fiatApprovalDTO);
			logger.info("Approval controller end");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false,StringConstant.Success, null, result);

		
	}
	/**
	 *  Deposit in Crypto wallet
	 * @param cryptoDepositDTO
	 * @return
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/wallets/crypto/deposit")

	public Map createCryptoDeposit(@RequestBody CryptoDepositDto cryptoDepositDTO) {
		Map result = null;
		

			result = walletService.createCryptoDeposit(cryptoDepositDTO);

			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

	}
	/**
	 *  Admin Approval for Crypto Deposit
	 * @param cryptoApprovalDTO
	 * @return
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/v1/admin/wallets/crypto/deposit/approval")

	public Map cryptoDepositApproval(@RequestBody CryptoApprovalDto cryptoApprovalDTO) {
		logger.info("Approval controller entered");
		Map result = null;
		

			result = walletService.cryptoDepositApproval(cryptoApprovalDTO);
			logger.info("Approval controller end");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

	}
	/**
	 *  Fiat Wallet Withdraw
	 * @param fiatWithdrawDTO
	 * @return
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/wallets/fiat/withdraw")

	public Map createFiatWithdraw(@RequestBody FiatWithdrawDto fiatWithdrawDTO) {
		logger.info("Approval controller entered");
		Map result = null;
		

			result = walletService.createFiatWithdraw(fiatWithdrawDTO);
			logger.info("Approval controller end");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false,StringConstant.Success, null, result);

	}
	/**
	 *  Crypto Wallet Withdraw
	 * @param cryptoWithdrawDTO
	 * @return
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/v1/user/wallets/crypto/withdraw")

	public Map createCryptoWithdraw(@RequestBody CryptoWithdrawDto cryptoWithdrawDTO) {
		logger.info("Approval controller entered");
		Map result = null;
		

			result = walletService.createCryptoWithdraw(cryptoWithdrawDTO);
			logger.info("Approval controller end");
			return ResponseHandler.generateResponse(HttpStatus.CREATED, false, StringConstant.Success, null, result);

	}

}
