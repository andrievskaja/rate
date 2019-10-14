package com.exchangerate.rate.dao;

import com.exchangerate.rate.model.CurrencyQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;

@Repository
public interface CurrencyQuoteRepository extends JpaRepository<CurrencyQuote,Long> {

   Optional<CurrencyQuote> findByCodeAndDateBetween(Integer code, Date start, Date end);

//   @Query(value = "from CurrencyQuote c where c.code =:code and c.date BETWEEN :start AND :end")
//    Optional<CurrencyQuote> findByCodeAndDateBetween(@Param("code")Integer code,@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);
}
