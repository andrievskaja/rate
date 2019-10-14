package com.exchangerate.rate.converters;

import com.exchangerate.rate.dto.CurrencyRateDto;
import com.exchangerate.rate.model.CurrencyQuote;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class CurrencyRateDtoToCurrencyQuote implements Converter<CurrencyRateDto, CurrencyQuote> {

    @Override
    public CurrencyQuote convert(CurrencyRateDto sours) {
        if (sours == null) {
            return null;
        }
        CurrencyQuote currencyQuote =
                new CurrencyQuote(null,
                        new Date(sours.getDate()*1000L),
                        sours.getCurrencyCodeA(),
                        sours.getRateBuy(),
                        sours.getRateSell());
        return currencyQuote;
    }
}
