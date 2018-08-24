package com.student.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.domain.Deposit;

public interface DepositRepository extends JpaRepository <Deposit , Long>{

}
