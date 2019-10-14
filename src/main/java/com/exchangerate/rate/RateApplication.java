package com.exchangerate.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class RateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateApplication.class, args);
    }
}
