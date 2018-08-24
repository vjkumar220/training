package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.Users;
import com.oodles.domain.Wallet;
@Repository
	public interface UserWalletRepository extends JpaRepository<Wallet, Integer> {
	}

