package com.exchangerate.rate.converters;

import com.exchangerate.rate.dto.CurrencyRateView;
import com.exchangerate.rate.model.CurrencyQuote;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyQuoteToCurrencyRateView implements Converter<CurrencyQuote, CurrencyRateView> {

    @Override
    public CurrencyRateView convert(CurrencyQuote sours) {

        CurrencyRateView currencyRateView = new CurrencyRateView(sours.getRateBuy(),sours.getRateSell());
        return currencyRateView;
    }
}
