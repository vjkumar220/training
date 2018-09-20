package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.FiatWithdraw;
@Repository
public interface FiatWithdrawRepository extends JpaRepository<FiatWithdraw, Long> {

}
