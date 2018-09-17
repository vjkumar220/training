package com.oodles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.CryptoDeposit;
import com.oodles.domain.CryptoWallet;
import com.oodles.domain.FiatDeposit;
import com.oodles.domain.FiatWallet;
import com.oodles.domain.User;
import com.oodles.dto.ApprovalDto;
import com.oodles.dto.CryptoApprovalDto;
import com.oodles.dto.CryptoDepositDto;
import com.oodles.dto.FiatDepositDto;
import com.oodles.enumeration.CryptoName;
import com.oodles.enumeration.DepositStatus;
import com.oodles.repository.CryptoDepositRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatDepositRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.UserRepository;

@Service
public class DepositService {
	Logger log = LoggerFactory.getLogger(DepositService.class);
	@Autowired
	private FiatDepositRepository fiatDepositRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FiatWalletRepository fiatWalletRepository;
	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	@Autowired
	private CryptoDepositRepository cryptoDepositRepository;

	/**
	 * Fiat deposit request generate
	 * 
	 * @param depositDto
	 * @return
	 */

	public Map<Object, Object> fiatDeposit(FiatDepositDto depositDto) {
		HashMap<Object, Object> result = new HashMap<>();
		Long userId = depositDto.getUserId();
		log.info("user value", userId);
		Double amount = depositDto.getAmount();
		log.info("user amount ", amount);
		Optional<User> findUser = userRepository.findById(userId);
		log.info("user find", findUser);
		if (findUser.isPresent()) {
			log.info("in if deposit service");
			FiatDeposit deposit = new FiatDeposit();
			deposit.setAmount(amount);
			deposit.setUser(findUser.get());
			deposit.setDepositStatus(DepositStatus.PENDING);
			fiatDepositRepository.save(deposit);
			log.info("request save");
			result.put("responseMessage", "Your request genrated");
			return result;
		}
		log.info("req not sAVED");
		result.put("responseMessage", "No user found");
		return result;
	}

	/**
	 * Get all pending fiat deposit list
	 * 
	 * @return
	 */

	public List<FiatDeposit> getAllPendingRequest() {
		return fiatDepositRepository.findAllByDepositStatus(DepositStatus.PENDING);
	}

	/**
	 * Approving the Deposit Request and update the fiat balance
	 * 
	 * @param approvalDto
	 * @return
	 */

	public Map<Object, Object> approveDeposit(ApprovalDto approvalDto) {
		HashMap<Object, Object> result = new HashMap<>();
		Long userIdDto = approvalDto.getUserId();
		DepositStatus depositStatus = approvalDto.getDepositStatus();
		Long depositId = approvalDto.getDepositId();
		Optional<User> user = userRepository.findById(userIdDto);
		// Checking user Id
		if (user.isPresent()) {
			Optional<FiatDeposit> fiatDeposit = fiatDepositRepository.findById(depositId);
			User foundUser = user.get();
			// Checking deposit Id
			if (fiatDeposit.isPresent()) {
				FiatDeposit foundDeposit = fiatDeposit.get();
				DepositStatus status = foundDeposit.getDepositStatus();
				Double amount = foundDeposit.getAmount();
				FiatDeposit foundDepositRequest = fiatDepositRepository.findByUserIdAndDepositId(foundUser.getId(),
						depositId);
				if (foundDepositRequest != null) {
					// Checking deposit status
					if (status.equals(DepositStatus.PENDING)) {
						if (depositStatus.equals(DepositStatus.APPROVED)) {
							FiatDeposit deposit = new FiatDeposit();
							deposit.setDepositId(depositId);
							deposit.setUser(foundUser);
							deposit.setDepositStatus(depositStatus.APPROVED);
							deposit.setAmount(amount);
							FiatWallet fiatWallet = fiatWalletRepository.findByUserId(foundUser.getId());
							Long fiatWalletId = fiatWallet.getFiatWalletId();
							String walletType = fiatWallet.getWalletType();
							String coinName = fiatWallet.getCoinName();
							Double balance = fiatWallet.getBalance();
							Double shadowBalance = fiatWallet.getShadowBalance();
							fiatWallet.setFiatWalletId(fiatWalletId);
							fiatWallet.setWalletType(walletType);
							fiatWallet.setCoinName(coinName);
							fiatWallet.setUser(foundUser);
							Double updatedBalance = (balance + amount);
							fiatWallet.setBalance(updatedBalance);
							Double updatedShadowBalance = (shadowBalance + amount);
							fiatWallet.setShadowBalance(updatedShadowBalance);
							fiatWalletRepository.save(fiatWallet);
							fiatDepositRepository.save(deposit);
							result.put("responseMessage", "Your request is Approved and Balance is Updated");
							return result;
						} else if (depositStatus.equals(DepositStatus.REJECTED)) {
							foundDeposit.setDepositStatus(depositStatus.REJECTED);
							fiatDepositRepository.save(foundDeposit);
							result.put("responseMessage", "Your request is Rejected");
							return result;

						}
					}
				}
			}
			result.put("responseMessage", "Deposit Request Not Found");
			return result;
		}
		result.put("responseMessage", "User Not Found");
		return result;
	}

	/**
	 * Crypto deposit request Generate
	 * 
	 * @param cryptoDepositDto
	 * @return
	 */

	public Map<Object, Object> cryptoDeposit(CryptoDepositDto cryptoDepositDto) {
		HashMap<Object, Object> result = new HashMap<>();
		CryptoName coinName = cryptoDepositDto.getCoinName();
		Double coinQuantity = cryptoDepositDto.getCoinQuantity();
		Long cryptoWalletId = cryptoDepositDto.getCryptoWalletId();
		Optional<CryptoWallet> foundWallet = cryptoWalletRepository.findById(cryptoWalletId);
		if (foundWallet.isPresent()) {
			CryptoDeposit cryptoDeposit = new CryptoDeposit();
			cryptoDeposit.setCoinType(coinName.toString());
			cryptoDeposit.setCryptoWallet(foundWallet.get());
			cryptoDeposit.setNumberOfCoin(coinQuantity);
			cryptoDeposit.setDepositStatus(DepositStatus.PENDING);
			cryptoDepositRepository.save(cryptoDeposit);
			result.put("responseMessage", "Your crypto Deposit generated");
			return result;
		}
		result.put("responseMessage", "Wallet is not found");
		return result;
	}

	/**
	 * Approved the crypto deposit request and update crypto wallet balanace
	 * 
	 * @param cryptoApprovalDto
	 * @return
	 */

	public Map<Object, Object> approveCryptoRequest(CryptoApprovalDto cryptoApprovalDto) {
		HashMap<Object, Object> result = new HashMap<>();
		DepositStatus depositStatusDto = cryptoApprovalDto.getDepositStatus();
		Long walletId = cryptoApprovalDto.getWalletId();
		Long depositId = cryptoApprovalDto.getDepositId();
		Optional<CryptoDeposit> foundDeposit = cryptoDepositRepository.findById(depositId);
		if (foundDeposit.isPresent()) {
			CryptoDeposit cryptoDeposit = foundDeposit.get();
			Double coinRequest = cryptoDeposit.getNumberOfCoin();
			DepositStatus status = cryptoDeposit.getDepositStatus();
			Optional<CryptoWallet> foundWallet = cryptoWalletRepository.findById(walletId);
			if (foundWallet.isPresent()) {
				CryptoWallet cryptoWallet = foundWallet.get();
				Double coinBalance = cryptoWallet.getBalance();
				Double coinShadowBalance = cryptoWallet.getShadowBalance();
				String coinName = cryptoWallet.getCoinName();
				CryptoDeposit checkDeposit = cryptoDepositRepository
						.findByCryptoWalletCryptoWalletIdAndCryptoDepositId(walletId, depositId);
				if (checkDeposit != null) {
					if (status.equals(DepositStatus.PENDING)) {
						if (depositStatusDto.equals(DepositStatus.APPROVED)) {
							Double updatedBalance = coinBalance + coinRequest;
							Double updatedShadowBalance = coinShadowBalance + coinRequest;
							cryptoWallet.setBalance(updatedBalance);
							cryptoWallet.setShadowBalance(updatedShadowBalance);
							cryptoDeposit.setDepositStatus(DepositStatus.APPROVED);
							cryptoDepositRepository.save(cryptoDeposit);
							cryptoWalletRepository.save(cryptoWallet);
							result.put("success", "Your request is approved and balance is updated");
						} else if (depositStatusDto.equals(DepositStatus.REJECTED)) {
							cryptoDeposit.setDepositStatus(DepositStatus.REJECTED);
							cryptoDepositRepository.save(cryptoDeposit);
							result.put("responseMessage", "Your request is rejected");
						}

					}
				}
			}
			result.put("responseMessage", "User is  not found");
		}
		result.put("responseMessage", "Order is not present");
		return result;
	}

}
