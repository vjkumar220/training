package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.models.FiatCurrency;

public interface FiatCurrencyRepository extends JpaRepository<FiatCurrency,Long>{

}
