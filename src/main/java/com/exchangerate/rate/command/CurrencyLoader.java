package com.exchangerate.rate.command;

import com.exchangerate.rate.dao.CurrencyRepository;
import com.exchangerate.rate.model.Currency;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class CurrencyLoader implements CommandLineRunner {

    private final CurrencyRepository currencyRepository;

    public CurrencyLoader(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        int result = currencyRepository.findAll().size();
        if (result == 0) {
            List<Currency> currencies = new ArrayList<>();
            Currency currencyUSD = new Currency(null, "USD", 840, "USD" );
            Currency currencyEUR = new Currency(null, "EUR", 978, "EUR" );
            currencies.add(currencyUSD);
            currencies.add(currencyEUR);
            currencyRepository.saveAll(currencies);
        }
    }
}
