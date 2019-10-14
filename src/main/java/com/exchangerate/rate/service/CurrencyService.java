package com.exchangerate.rate.service;

import com.exchangerate.rate.dao.CurrencyRepository;
import com.exchangerate.rate.exception.EntityNotFounException;
import com.exchangerate.rate.model.Currency;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Cacheable(cacheNames = "currency", key = "#mnemonics")
    public Currency findByMnemonics(String mnemonics) {
        Optional<Currency> currency = currencyRepository.findByMnemonics(mnemonics);
        currency.orElseThrow(() -> new EntityNotFounException(String.format("Currency with mnemonics = %s not fount", mnemonics)));
        return currency.get();
    }


}
