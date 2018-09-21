package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.domain.ProfitTable;

@Repository
public interface ProfitTableRepository extends JpaRepository<ProfitTable, Long>{

}
