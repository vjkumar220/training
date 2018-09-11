package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.DTO.CryptoDepositDTO;
import com.oodles.DTO.FiatApprovalDTO;
import com.oodles.DTO.FiatDepositDTO;
import com.oodles.DTO.UserWalletDTO;
import com.oodles.models.CryptoDeposit;
import com.oodles.models.CryptoWallet;
import com.oodles.models.DepositStatus;
import com.oodles.models.FiatDeposit;
import com.oodles.models.FiatWallet;
import com.oodles.models.User;
import com.oodles.repository.CryptoDepositRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatDepositRepository;
import com.oodles.repository.FiatWalletRepository;
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
	// create a Fiat wallet
	public Map<String, Object> createFiatWallet(UserWalletDTO userWalletDTO) {
		logger.info("createFiatwallet entered");
		Map<String, Object> result = new HashMap<String, Object>();
		String coinName = userWalletDTO.getCoinName();
		String walletType = userWalletDTO.getWalletType();
		logger.info(" walletType in service =" + walletType);
		Long balance = userWalletDTO.getBalance();
		Long shadowBalance = userWalletDTO.getShadowBalance();
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

	// Create Crypto Wallet

	public Map<String, Object> createCryptoWallet(UserWalletDTO userWalletDTO) {
		logger.info("createFiatwallet entered");
		Map<String, Object> result = new HashMap<String, Object>();
		String coinName = userWalletDTO.getCoinName();
		String walletType = userWalletDTO.getWalletType();
		logger.info(" walletType in service =" + walletType);
		Long balance = userWalletDTO.getBalance();
		Long shadowBalance = userWalletDTO.getShadowBalance();
		Long userId = userWalletDTO.getUser_id();
		// logger
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			User foundUser = user.get();

			CryptoWallet fwType = cryptoWalletRepository.findByWalletTypeAndUser(walletType, foundUser);

			if (!(fwType == null))
			// if(fwType.equals(walletType))
			{
				CryptoWallet newCryptoWallet = new CryptoWallet();
				newCryptoWallet.setBalance(balance);
				newCryptoWallet.setCoinName(coinName);
				newCryptoWallet.setShadowBalance(shadowBalance);
				newCryptoWallet.setWalletType(walletType);

				newCryptoWallet.setUser(foundUser);
				cryptoWalletRepository.save(newCryptoWallet);
				result.put("responseMessage", "success");
			}

		}
		return result;
	}

	// fiat Wallet Deposit

	public Map<String, Object> createFiatDeposit(FiatDepositDTO fiatDepositDTO) {
		logger.info("create deposit request");
		Map<String, Object> result = new HashMap<String, Object>();
		String walletType = fiatDepositDTO.getWalletType();
		Long amount = fiatDepositDTO.getAmount();
		Long userId = fiatDepositDTO.getUserId();
		// logger
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {

			User foundUser = user.get();

			FiatDeposit fwType = fiatDepositRepository.findByWalletTypeAndUser(walletType, foundUser);

			FiatDeposit newFiatDeposit = new FiatDeposit();
			newFiatDeposit.setAmount(amount);
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

	// Admin Approval

	public Map<String, Object> fiatDepositApproval(FiatApprovalDTO fiatApprovalDTO) {
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
					Long amount = deposituser.getAmount();
					logger.info("deposit amount" + amount);
					DepositStatus status = deposituser.getStatus();
					deposituser.setStatus(status.APPROVED);
					FiatWallet fwType = fiatWalletRepository.findByUserId(foundUser.getId());
					Long currentBalance = fwType.getBalance();
					logger.info("Current Amount" + currentBalance);
					Long currentShadowBalance = fwType.getShadowBalance();
					Long updatedBalance = currentBalance + amount;
					fwType.setBalance(updatedBalance);
					Long updatedShadowBalance = currentShadowBalance + amount;
					fwType.setShadowBalance(updatedShadowBalance);
					fiatWalletRepository.save(fwType);
					logger.info("updation service done");
					result.put("responseMessage", "success");
					return result;
				} else if (newstatus.equalsIgnoreCase("REJECT")) {
					DepositStatus status = deposituser.getStatus();
					deposituser.setStatus(status.REJECT);
					result.put("responseMessage", "success");
					return result;
				}
			}
			result.put("responseMessage", "There is no request for Deposit");
			return result;
		}
		return result;

	}
	
	//Create crypto deposit
	public Map<String, Object> createCryptoDeposit(CryptoDepositDTO cryptoDepositDTO) {
		logger.info("create deposit request");
		Map<String, Object> result = new HashMap<String, Object>();
		 double numberOfCoin = cryptoDepositDTO.getNumberOfCoin();
		 Long walletId=cryptoDepositDTO.getWalletId();
		// logger
		Optional<CryptoWallet> cryptowallet = cryptoWalletRepository.findById(walletId);

		if (cryptowallet.isPresent()) {

			CryptoWallet foundUser = cryptowallet.get();

			/*CryptoDeposit fwType = cryptoDepositRepository.findByWallet(foundUser);*/

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

	
	
	
	
	
	
	
	
	
	
	
	
	
}
