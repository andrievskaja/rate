package com.exchangerate.rate.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyQuote {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    /**
     * code currency  USD(840), EUR(978), UAH(980)
     */
    private int code;

    private BigDecimal rateBuy;

    private BigDecimal rateSell;


}
