package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.Wallet;

	public interface UserWalletRepository extends JpaRepository<Wallet, Integer> {
	}

