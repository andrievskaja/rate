package com.exchangerate.rate.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

    private static String ZONE_ID = "Europe/Kiev";

    public static Date convertToDate(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.of(ZONE_ID))
                .toInstant());
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return java.util.Date.from(localDateTime
                .atZone(ZoneId.of(ZONE_ID)).toInstant());
    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of(ZONE_ID))
                .toLocalDateTime();
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of(ZONE_ID))
                .toLocalDate();
    }
}
