package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.domain.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{
}