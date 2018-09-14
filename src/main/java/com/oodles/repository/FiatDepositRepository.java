package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.FiatDeposit;
import com.oodles.domain.User;
import com.oodles.enumeration.DepositStatus;

public interface FiatDepositRepository extends JpaRepository<FiatDeposit, Long>{

		List<FiatDeposit> findAllByDepositStatus(DepositStatus depositStatus);
		
		FiatDeposit findByUserIdAndDepositId(Long id, Long depositId);
		
		List<FiatDeposit> findAllByUserId(Long id);
}
