package com.exchangerate.rate.converters;

import com.exchangerate.rate.dto.CurrencyRateDto;
import com.exchangerate.rate.dto.CurrencyRateView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRateDtoToCurrencyRateView implements Converter<CurrencyRateDto, CurrencyRateView> {

    @Override
    public CurrencyRateView convert(CurrencyRateDto sours) {
        CurrencyRateView currencyRateView = new CurrencyRateView(sours.getRateBuy(),sours.getRateSell());
        return currencyRateView;
    }
}
