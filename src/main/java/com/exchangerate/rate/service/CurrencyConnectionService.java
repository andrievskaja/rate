package com.exchangerate.rate.service;

import com.exchangerate.rate.controller.RateController;
import com.exchangerate.rate.dto.CurrencyRateDto;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyConnectionService {

    public static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CurrencyConnectionService.class);

    @Value("${api.endpoint}")
    private String bankUrl;

    @Value("${api.rate}")
    private String rateUrl;

    public List<CurrencyRateDto> getRate() {
        CurrencyClient client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(CurrencyClient.class))
                .logLevel(Logger.Level.FULL)
                .target(CurrencyClient.class, bankUrl + rateUrl);

        List<CurrencyRateDto> rateDtoList = client.getCurrencies();
        LOG.info(rateDtoList.toString());
        return rateDtoList;
    }
}
