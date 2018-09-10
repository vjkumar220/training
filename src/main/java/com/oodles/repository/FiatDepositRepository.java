package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.FiatDeposit;

public interface FiatDepositRepository extends JpaRepository<FiatDeposit, Long>{

}
