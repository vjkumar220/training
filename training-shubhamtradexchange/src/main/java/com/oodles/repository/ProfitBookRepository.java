package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oodles.models.ProfitBook;
@Repository
public interface ProfitBookRepository  extends JpaRepository<ProfitBook,Long>{

}
