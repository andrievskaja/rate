package com.exchangerate.rate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateView {
    private BigDecimal rateBuy;
    private BigDecimal rateSell;
}
