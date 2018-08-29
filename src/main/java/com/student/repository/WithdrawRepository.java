package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.domain.Withdraw;

public interface WithdrawRepository extends JpaRepository<Withdraw , Long> {

}
