package com.exchangerate.rate.service;

import com.exchangerate.rate.dto.CurrencyRateDto;
import feign.RequestLine;

import java.util.List;

public interface CurrencyClient {
    @RequestLine("GET")
    List<CurrencyRateDto> getCurrencies();
}
