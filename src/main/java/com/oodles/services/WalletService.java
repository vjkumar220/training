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
import com.oodles.dto.CryptoWalletDto;
import com.oodles.dto.CryptoWithdrawDto;
import com.oodles.dto.FiatApprovalDto;
import com.oodles.dto.FiatDepositDto;
import com.oodles.dto.FiatWithdrawDto;
import com.oodles.enums.CryptoCoin;
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
	public Map<String, Object> createFiatWallet(Long userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("In service fiat wallet");
		//Long userId = fiatWallet.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User foundUser = user.get();
			FiatWallet newWalletType = fiatWalletRepository.findByUser(foundUser);
			if (newWalletType == null) {
				FiatWallet wallet = new FiatWallet();
				wallet.setCoinName("INR");
				wallet.setWalletType("Fiat");
				wallet.setShadowBalance(0.0);
				wallet.setBalance(0.0);
				wallet.setUser(foundUser);
				fiatWalletRepository.save(wallet);
				result.put("responseMessage", "Fiat wallet is created");
				return result;
			}
			result.put("responseMessage", "User's fiat wallet is already there");
			return result;
		}
		result.put("responseMessage", "User Not Found");
		return result;
	}

	/**
	 *  Create Crypto Wallet
	 * @param userWalletDTO
	 * @return
	 */

	public Map<String, Object> createCryptoWallet(CryptoWalletDto cryptoWallet) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("In service crypto wallet");
		CryptoCoin coinName = cryptoWallet.getCoinName();
		Long userId = cryptoWallet.getUserId();
		

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			logger.info("in user if");
			User foundUser = user.get();
			CryptoWallet newWalletType = cryptoWalletRepository.findByCoinNameAndUserId(coinName.toString(), foundUser.getId());
			
			if (newWalletType == null) {
				logger.info("logger in newWalletType");
				CryptoWallet wallet = new CryptoWallet();
				wallet.setBalance(0.0);
				wallet.setCoinName(coinName.toString());
				wallet.setShadowBalance(0.0);
				wallet.setUser(foundUser);
				wallet.setWalletType("Crypto");
				cryptoWalletRepository.save(wallet);
				logger.info("seding result by service");
				result.put("responseMessage", "Crypto wallet is created");
				return result;
			}
			result.put("responseMessage", " crypto wallet alredy present");
			return result;
		}
		result.put("responseMessage", "User Not Found");
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
