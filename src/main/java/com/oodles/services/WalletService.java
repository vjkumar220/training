package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.dto.CryptoApprovalDto;
import com.oodles.dto.CryptoDepositDto;
import com.oodles.dto.CryptoWithdrawDto;
import com.oodles.dto.FiatApprovalDto;
import com.oodles.dto.FiatDepositDto;
import com.oodles.dto.FiatWalletDto;
import com.oodles.dto.FiatWithdrawDto;
import com.oodles.dto.UserWalletDto;
import com.oodles.enums.DepositStatus;
import com.oodles.models.CryptoDeposit;
import com.oodles.models.CryptoWallet;
import com.oodles.models.CryptoWithdraw;
import com.oodles.models.FiatDeposit;
import com.oodles.models.FiatWallet;
import com.oodles.models.FiatWithdraw;
import com.oodles.models.User;
import com.oodles.repository.CryptoDepositRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.CryptoWithdrawRepository;
import com.oodles.repository.FiatDepositRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.FiatWithdrawRepository;
import com.oodles.repository.UserRepository;

@Service
public class WalletService {
	private Logger logger = LoggerFactory.getLogger(WalletService.class);
	@Autowired
	private FiatWalletRepository fiatWalletRepository;

	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FiatDepositRepository fiatDepositRepository;
	@Autowired
	private CryptoDepositRepository cryptoDepositRepository;
	@Autowired
	private FiatWithdrawRepository fiatWithdrawRepository;
	@Autowired
	private CryptoWithdrawRepository cryptoWithdrawRepository;

	/**
	 *  create a Fiat wallet
	 * @param userWalletDTO
	 * @return
	 */
	public Map<String, Object> createFiatWallet(FiatWalletDto userWalletDTO) {
		logger.info("createFiatwallet entered");
		Map<String, Object> result = new HashMap<String, Object>();
		String coinName = (userWalletDTO.getCoinName()).toString();
		String walletType = (userWalletDTO.getWalletType()).toString();
		logger.info(" walletType in service =" + walletType);
		Double balance = userWalletDTO.getBalance();
		Double shadowBalance = userWalletDTO.getShadowBalance();
		Long userId = userWalletDTO.getUser_id();
		// logger
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			User foundUser = user.get();

			FiatWallet fwType = fiatWalletRepository.findByWalletTypeAndUser(walletType, foundUser);

			if (fwType == null)
			// if(fwType.equals(walletType))
			{
				FiatWallet newFiatWallet = new FiatWallet();
				newFiatWallet.setBalance(balance);
				newFiatWallet.setCoinName(coinName);
				newFiatWallet.setShadowBalance(shadowBalance);
				newFiatWallet.setWalletType(walletType);

				newFiatWallet.setUser(foundUser);
				fiatWalletRepository.save(newFiatWallet);
				result.put("responseMessage", "success");
			}

		}
		return result;
	}

	/**
	 *  Create Crypto Wallet
	 * @param userWalletDTO
	 * @return
	 */

	public Map<String, Object> createCryptoWallet(UserWalletDto userWalletDTO) {
		logger.info("create Crypto wallet entered");
		Map<String, Object> result = new HashMap<String, Object>();
		String coinName = (userWalletDTO.getCoinName()).toString();
		String walletType = (userWalletDTO.getWalletType()).toString();
		logger.info(" walletType in service =" + walletType);
		Double balance = userWalletDTO.getBalance();
		Double shadowBalance = userWalletDTO.getShadowBalance();
		Long userId = userWalletDTO.getUser_id();
		// logger
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			User foundUser = user.get();
			logger.info("user found");
			CryptoWallet fwType = cryptoWalletRepository.findByWalletTypeAndUser(walletType, foundUser);

			if (!(fwType == null))
			// if(fwType.equals(walletType))
			{
				logger.info("WalletType search done");
				CryptoWallet newCryptoWallet = new CryptoWallet();
				newCryptoWallet.setBalance(balance);
				newCryptoWallet.setCoinName(coinName);
				newCryptoWallet.setShadowBalance(shadowBalance);
				newCryptoWallet.setWalletType(walletType);

				newCryptoWallet.setUser(foundUser);
				cryptoWalletRepository.save(newCryptoWallet);
				logger.info("save done");
				result.put("responseMessage", "success");
			}

		}
		return result;
	}

	/**
	 *  fiat Wallet Deposit
	 * @param fiatDepositDTO
	 * @return
	 */

	public Map<String, Object> createFiatDeposit(FiatDepositDto fiatDepositDTO) {
		logger.info("create deposit request");
		Map<String, Object> result = new HashMap<String, Object>();
		String walletType = (fiatDepositDTO.getWalletType()).toString();
		Double depositamount = fiatDepositDTO.getAmount();
		Long userId = fiatDepositDTO.getUserId();
		// logger
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			User foundUser = user.get();

			FiatDeposit fwType = fiatDepositRepository.findByWalletTypeAndUser(walletType, foundUser);

			FiatDeposit newFiatDeposit = new FiatDeposit();
			newFiatDeposit.setAmount(depositamount);
			newFiatDeposit.setWalletType(walletType);
			DepositStatus status = newFiatDeposit.getStatus();
			newFiatDeposit.setStatus(status.PENDING);
			newFiatDeposit.setUser(foundUser);
			fiatDepositRepository.save(newFiatDeposit);
			result.put("responseMessage", "success");
			return result;
		}
		result.put("responseMessage", "user id donot exist");
		return result;

	}

	/**
	 *  Admin Approval
	 * @param fiatApprovalDTO
	 * @return
	 */

	public Map<String, Object> fiatDepositApproval(FiatApprovalDto fiatApprovalDTO) {
		logger.info(" Approve service entered");
		Map<String, Object> result = new HashMap<String, Object>();
		String newstatus = fiatApprovalDTO.getStatus().toString();
		Long userId = fiatApprovalDTO.getUserId();
		Long transId = fiatApprovalDTO.getTransactionID();
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			logger.info("user search");
			User foundUser = user.get();
			logger.info("user found");
			FiatDeposit deposituser = fiatDepositRepository.findByTransactionIdAndUserId(transId, foundUser.getId());
			logger.info("user for deposit");

			if (deposituser != null) {
				if (newstatus.equalsIgnoreCase("APPROVED")) {
					logger.info("status ");
					Double depositamount = deposituser.getAmount();
					logger.info("deposit amount" + depositamount);
					DepositStatus status = deposituser.getStatus();
					deposituser.setStatus(status.APPROVED);
					FiatWallet fwType = fiatWalletRepository.findByUserId(foundUser.getId());
					Double currentBalance = fwType.getBalance();
					logger.info("Current Amount" + currentBalance);
					Double currentShadowBalance = fwType.getShadowBalance();
					Double updatedBalance = currentBalance + depositamount;
					fwType.setBalance(updatedBalance);
					Double updatedShadowBalance = currentShadowBalance + depositamount;
					fwType.setShadowBalance(updatedShadowBalance);
					fiatWalletRepository.save(fwType);
					logger.info("updation service done");
					result.put("responseMessage", "success");
					return result;
				} else if (newstatus.equalsIgnoreCase("REJECT")) {
					DepositStatus status = deposituser.getStatus();
					deposituser.setStatus(status.REJECT);
					fiatDepositRepository.save(deposituser);
					result.put("responseMessage", "success");
					return result;
				}
			}
			result.put("responseMessage", "There is no request for Deposit");
			return result;
		}
		return result;

	}

	/**
	 *  Create crypto deposit
	 * @param cryptoDepositDTO
	 * @return
	 */
	public Map<String, Object> createCryptoDeposit(CryptoDepositDto cryptoDepositDTO) {
		logger.info("create deposit request");
		Map<String, Object> result = new HashMap<String, Object>();
		Double numberOfCoin = cryptoDepositDTO.getNumberOfCoin();
		Long walletId = cryptoDepositDTO.getWalletId();
		// logger
		Optional<CryptoWallet> cryptowallet = cryptoWalletRepository.findById(walletId);

		if (cryptowallet.isPresent()) {

			CryptoWallet foundUser = cryptowallet.get();

			/* CryptoDeposit fwType = cryptoDepositRepository.findByWallet(foundUser); */

			CryptoDeposit newCryptoDeposit = new CryptoDeposit();
			newCryptoDeposit.setNumberOfCoin(numberOfCoin);

			DepositStatus status = newCryptoDeposit.getStatus();
			newCryptoDeposit.setStatus(status.PENDING);
			newCryptoDeposit.setCryptowallet(foundUser);
			cryptoDepositRepository.save(newCryptoDeposit);
			result.put("responseMessage", "success");
			return result;
		}
		result.put("responseMessage", "wallet id does not exist");
		return result;

	}

	/**
	 * Admin Approval for Crypto deposit
	 * @param cryptoApprovalDTO
	 * @return
	 */

	public Map<String, Object> cryptoDepositApproval(CryptoApprovalDto cryptoApprovalDTO) {
		logger.info(" Approve service entered");
		Map<String, Object> result = new HashMap<String, Object>();
		String newstatus = cryptoApprovalDTO.getStatus().toString();
		Long walletid = cryptoApprovalDTO.getWalletId();
		Long transId = cryptoApprovalDTO.getTransactionID();
		Optional<CryptoWallet> wallet = cryptoWalletRepository.findById(walletid);

		if (wallet.isPresent()) {
			logger.info("user search");
			CryptoWallet foundWallet = wallet.get();
			logger.info("user found");
			CryptoDeposit deposituser = cryptoDepositRepository.findByTransactionIdAndCryptowalletWalletId(transId,
					foundWallet.getWalletId());
			logger.info("user for deposit");
			if (deposituser != null) {
				if (newstatus.equalsIgnoreCase("APPROVED")) {
					logger.info("status ");
					Double amount = deposituser.getNumberOfCoin();
					logger.info("deposit amount" + amount);
					DepositStatus status = deposituser.getStatus();
					deposituser.setStatus(status.APPROVED);
					CryptoWallet fwType = cryptoWalletRepository.findByWalletId(foundWallet.getWalletId());
					Double currentBalance = fwType.getBalance();
					logger.info("Current Amount" + currentBalance);
					Double currentShadowBalance = fwType.getShadowBalance();
					Double updatedBalance = (currentBalance + amount);
					fwType.setBalance(updatedBalance);
					Double updatedShadowBalance = currentShadowBalance + amount;
					fwType.setShadowBalance(updatedShadowBalance);
					cryptoWalletRepository.save(fwType);
					logger.info("updation service done");
					result.put("responseMessage", "success");
					return result;
				} else if (newstatus.equalsIgnoreCase("REJECT")) {
					DepositStatus status = deposituser.getStatus();
					deposituser.setStatus(status.REJECT);
					cryptoDepositRepository.save(deposituser);
					result.put("responseMessage", "success");
					return result;
				}
			}
			result.put("responseMessage", "There is no request for Deposit");
			return result;
		}
		return result;

	}

	/**
	 *  Fiat Withdraw
	 * @param fiatWithdrawDTO
	 * @return
	 */
	public Map<String, Object> createFiatWithdraw(FiatWithdrawDto fiatWithdrawDTO) {
		logger.info("create withdraw request");
		Map<String, Object> result = new HashMap<String, Object>();
		Double withdrawnamount = fiatWithdrawDTO.getAmount();
		Long walletId = fiatWithdrawDTO.getWalletId();
		// logger
		Optional<FiatWallet> fiatwallet = fiatWalletRepository.findById(walletId);

		if (fiatwallet.isPresent()) {

			FiatWallet foundUser = fiatwallet.get();

			/* FiatWithdraw fwType = fiatWithdrawRepository.findByWallet(foundUser); */

			FiatWithdraw newFiatWithdraw = new FiatWithdraw();
			newFiatWithdraw.setAmount(withdrawnamount);
			newFiatWithdraw.setFiatWallet(foundUser);
			fiatWithdrawRepository.save(newFiatWithdraw);
			FiatWallet fwType = fiatWalletRepository.findByWalletId(foundUser.getWalletId());
			Double currentBalance = fwType.getBalance();
			logger.info("Current Amount" + currentBalance);
			Double currentShadowBalance = fwType.getShadowBalance();
			if (currentShadowBalance >= withdrawnamount) {
				Double updatedBalance = (currentBalance - withdrawnamount);
				fwType.setBalance(updatedBalance);
				Double updatedShadowBalance = currentShadowBalance - withdrawnamount;
				fwType.setShadowBalance(updatedShadowBalance);
				fiatWalletRepository.save(fwType);
				result.put("responseMessage", "success");
				return result;
			}
			result.put("responseMessage", "Not Sufficient Amount");
			return result;
		}
		result.put("responseMessage", "Invalid ID");
		return result;

	}

	/**
	 *  Crypto wallet Withdraw
	 * @param cryptoWithdrawDTO
	 * @return
	 */
	public Map<String, Object> createCryptoWithdraw(CryptoWithdrawDto cryptoWithdrawDTO) {
		logger.info("create withdraw request");
		Map<String, Object> result = new HashMap<String, Object>();
		Double withdrawnamount = cryptoWithdrawDTO.getNumberOfCoin();
		Long walletId = cryptoWithdrawDTO.getWalletId();
		// logger
		Optional<CryptoWallet> cryptowallet = cryptoWalletRepository.findById(walletId);

		if (cryptowallet.isPresent()) {

			CryptoWallet foundUser = cryptowallet.get();

			/* FiatWithdraw fwType = fiatWithdrawRepository.findByWallet(foundUser); */

			CryptoWithdraw newCryptoWithdraw = new CryptoWithdraw();
			newCryptoWithdraw.setQuantity(withdrawnamount);
			newCryptoWithdraw.setCryptowallet(foundUser);
			cryptoWithdrawRepository.save(newCryptoWithdraw);
			CryptoWallet fwType = cryptoWalletRepository.findByWalletId(foundUser.getWalletId());
			Double currentBalance = fwType.getBalance();
			logger.info("Current Amount" + currentBalance);
			Double currentShadowBalance = fwType.getShadowBalance();
			if  (currentShadowBalance >= withdrawnamount){
				Double updatedBalance = (currentBalance - withdrawnamount);
				fwType.setBalance(updatedBalance);
				Double updatedShadowBalance = currentShadowBalance - withdrawnamount;
				fwType.setShadowBalance(updatedShadowBalance);
				cryptoWalletRepository.save(fwType);
				result.put("responseMessage", "success");
				return result;
			}
			result.put("responseMessage", "Not Sufficient Amount");
			return result;
		}
		result.put("responseMessage", "Invalid ID");
		return result;

	}
}
