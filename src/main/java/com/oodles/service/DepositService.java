package com.oodles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.deposit.CryptoDeposit;
import com.oodles.domain.deposit.FiatDeposit;
import com.oodles.domain.user.User;
import com.oodles.domain.wallet.FiatWallet;
import com.oodles.dto.ApprovalDto;
import com.oodles.dto.CryptoDepositDto;
import com.oodles.dto.FiatDepositDto;
import com.oodles.enumeration.DepositStatus;
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

	private HashMap<Object, Object> result = new HashMap<>();

	// Fiat deposit request generate
	
	public Map<Object, Object> fiatDeposit(FiatDepositDto depositDto) {
		log.info("In deposit service");
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
			result.put("success", "Your request genrated");
			return result;
		}
		log.info("req not sAVED");
		result.put("error", "No user found");
		return result;
	}

	// Get all pending fiat deposit list
	
	public List<FiatDeposit> getAllPendingRequest() {
		List<FiatDeposit> depositsStatus = fiatDepositRepository.findAllByDepositStatus(DepositStatus.PENDING);
		return depositsStatus;
	}

	// Approving the Deposit Request and update the fiat balance

	public Map<Object, Object> approveDeposit(ApprovalDto approvalDto) {
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
							fiatWallet.setFiatWalletId(fiatWalletId);
							fiatWallet.setWalletType(walletType);
							fiatWallet.setCoinName(coinName);
							fiatWallet.setUser(foundUser);
							Double updatedBalance = balance + amount;
							fiatWallet.setBalance(updatedBalance);
							fiatWalletRepository.save(fiatWallet);
							fiatDepositRepository.save(deposit);
							result.put("success", "Your request is Approved and Balance is Updated");
						}
					}
				}
			}
			result.put("error", "Deposit Request Not Found");
		}
		result.put("error", "User Not Found");
		return result;
	}

//     Crypto deposit request Genrate
	
	public Map<Object, Object> cryptoDeposit(CryptoDepositDto cryptoDepositDto){
		String coinName = cryptoDepositDto.getCoinName();
		Long userId = cryptoDepositDto.getUserId();
		Double coinQuantity = cryptoDepositDto.getCoinQuantity();
		return null;
	}








}
