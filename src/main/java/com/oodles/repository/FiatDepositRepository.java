package com.oodles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.FiatDeposit;
import com.oodles.domain.OrderStatus;
import com.oodles.enumeration.DepositStatus;

public interface FiatDepositRepository extends JpaRepository<FiatDeposit, Long>{
		List<FiatDeposit>  findByDepositStatus(OrderStatus depositStatus);
}
