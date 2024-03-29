package com.exchangerate.rate.dao;

import com.exchangerate.rate.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {
   Optional<Currency> findByMnemonics(String mnemonics);
}
