package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.CryptoWithdraw;
@Repository
public interface CryptoWithdrawRepository extends JpaRepository<CryptoWithdraw, Long> {


}
