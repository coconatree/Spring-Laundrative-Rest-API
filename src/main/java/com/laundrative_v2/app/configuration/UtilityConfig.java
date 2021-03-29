package com.laundrative_v2.app.configuration;

import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UtilityConfig
{
    // This file contains all the public and protected static final variables for the utility class

    // Followings are static variables are about the date time processes

    public final static DateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    public final static DateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd");
    public final static ZoneId ZONE_ID_TR = ZoneId.of("+03:00");
    public final static DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterFactory("dd.MM.yyyy HH:mm").createDateTimeFormatter();
}
