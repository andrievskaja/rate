package com.exchangerate.rate.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * USD,EUR
     */
    private String mnemonics;

    /**
     * code currency  USD(840), EUR(978), UAH(980)
     */
    private Integer code;

    private String description;

}
