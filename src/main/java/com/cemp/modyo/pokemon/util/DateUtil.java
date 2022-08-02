package com.cemp.modyo.pokemon.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {}

    private static final String DATE_FORMAT_PATTERN =  "MMM dd, yyyy hh:mm:ss a";
    public static String getTimeStamp(LocalDateTime localDateTime) {
        //"Nov 16, 2021 12:51:43 PM"
        DateTimeFormatter formatter = getFormatter();
        return localDateTime.format(formatter);
    }

    public static String getTimeStamp() {
        return getTimeStamp(getLocalDateTime());
    }

    private static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
    }
    private static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}
