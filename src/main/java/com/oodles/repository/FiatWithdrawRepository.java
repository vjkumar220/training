package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.FiatWithdraw;

public interface FiatWithdrawRepository extends JpaRepository<FiatWithdraw, Long> {

}
