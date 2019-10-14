package com.exchangerate.rate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CurrencyRateDto {

    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private BigDecimal rateBuy;
    private BigDecimal rateSell;
}
