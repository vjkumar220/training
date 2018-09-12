package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.withdraw.CryptoWithdraw;

public interface CryptoWithdrawRepository extends JpaRepository<CryptoWithdraw, Long> {


}
