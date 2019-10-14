package com.exchangerate.rate.controller;

import com.exchangerate.rate.dto.CurrencyRateDto;
import com.exchangerate.rate.dto.CurrencyRateView;
import com.exchangerate.rate.service.CurrencyQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


@RestController
@RequestMapping(value = "/rate", produces = MediaType.APPLICATION_JSON_VALUE)
public class RateController {

    public static final Logger LOG = LoggerFactory.getLogger(RateController.class);

    private final CurrencyQuoteService currencyQuoteService;

    public RateController(CurrencyQuoteService currencyQuoteService) {
        this.currencyQuoteService = currencyQuoteService;
    }

    @RequestMapping(value = "/{currency}", method = RequestMethod.GET)
    public ResponseEntity<?> getRate(@PathVariable(value = "currency") String currency) {
        LOG.info(currency);
        try {
            CurrencyRateView currencyRateView = currencyQuoteService.findCurrencyByCodeAndDate(currency);
            return ResponseEntity.ok().body(currencyRateView);
        }catch (Exception e){
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
