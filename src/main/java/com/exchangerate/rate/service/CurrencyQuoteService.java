package com.exchangerate.rate.service;

import com.exchangerate.rate.converters.CurrencyQuoteToCurrencyRateView;
import com.exchangerate.rate.converters.CurrencyRateDtoToCurrencyQuote;
import com.exchangerate.rate.converters.CurrencyRateDtoToCurrencyRateView;
import com.exchangerate.rate.dao.CurrencyQuoteRepository;
import com.exchangerate.rate.dto.CurrencyRateDto;
import com.exchangerate.rate.dto.CurrencyRateView;
import com.exchangerate.rate.model.Currency;
import com.exchangerate.rate.model.CurrencyQuote;
import com.exchangerate.rate.utils.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyQuoteService {
    public static final Logger LOG = LoggerFactory.getLogger(CurrencyQuoteService.class);
    private final CurrencyService currencyService;
    private final CurrencyQuoteRepository currencyQuoteRepository;
    private final CurrencyRateDtoToCurrencyQuote currencyRateDtoToCurrencyQuote;
    private final CurrencyQuoteToCurrencyRateView currencyQuoteToCurrencyRateView;
    private final CurrencyConnectionService currencyConnectionService;
    private final CurrencyRateDtoToCurrencyRateView currencyRateDtoToCurrencyRateView;

    public CurrencyQuoteService(CurrencyService currencyService,
                                CurrencyQuoteRepository currencyQuoteRepository,
                                CurrencyRateDtoToCurrencyQuote currencyRateDtoToCurrencyQuote,
                                CurrencyQuoteToCurrencyRateView currencyQuoteToCurrencyRateView,
                                CurrencyConnectionService currencyConnectionService,
                                CurrencyRateDtoToCurrencyRateView currencyRateDtoToCurrencyRateView) {
        this.currencyService = currencyService;
        this.currencyRateDtoToCurrencyQuote = currencyRateDtoToCurrencyQuote;
        this.currencyQuoteToCurrencyRateView = currencyQuoteToCurrencyRateView;
        this.currencyConnectionService = currencyConnectionService;
        this.currencyQuoteRepository = currencyQuoteRepository;
        this.currencyRateDtoToCurrencyRateView = currencyRateDtoToCurrencyRateView;
    }

    @Cacheable(cacheNames = "currency_quote", key = "#mnemonics")
    public CurrencyRateView findCurrencyByCodeAndDate(String mnemonics) {
        Currency currency = currencyService.findByMnemonics(mnemonics);
        LocalDateTime start = LocalDate.now().atTime(0, 0);
        LocalDateTime end = LocalDate.now().atTime(11, 59);
        Optional<CurrencyQuote> currencyQuote = currencyQuoteRepository.findByCodeAndDateBetween(
                currency.getCode(),
                DateConverter.convertToDate(start), DateConverter.convertToDate(end));
        if (!currencyQuote.isPresent()) {
            CurrencyRateDto rateDto = this.getCurrencyRate(currency.getCode());
            updateCurrencyQuote(rateDto);
            return currencyRateDtoToCurrencyRateView.convert(rateDto);
        }
        return currencyQuoteToCurrencyRateView.convert(currencyQuote.get());
    }

    /**
     * return actual currency rate from  https://api.monobank.ua/docs/ and filter bu code currency
     */
    public CurrencyRateDto getCurrencyRate(Integer code) {
        CurrencyRateDto currencyRateDto = null;
        try {
            List<CurrencyRateDto> rateDtoList = currencyConnectionService.getRate();
            currencyRateDto = rateDtoList.stream().filter(one -> one.getCurrencyCodeA() == code).findAny().get();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return currencyRateDto;
    }

    public void updateCurrencyQuote(CurrencyRateDto rateDto) {
        if (rateDto == null) {
            return;
        }
        currencyQuoteRepository.save(currencyRateDtoToCurrencyQuote.convert(rateDto));
    }
}
