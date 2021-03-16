package com.laundrative_v2.app.util;

import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utility
{
    private final static DateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private final static DateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd");
    private final static ZoneId ZONE_ID_TR = ZoneId.of("+03:00");
    private final static DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterFactory("dd.MM.yyyy HH:mm")
            .createDateTimeFormatter();


    public static ResponseEntity<Object> createResponse(String key, Object body, HttpStatus status)
    {
        HttpHeaders header = new HttpHeaders();
        header.set("ACCESS_KEY", key);

        return new ResponseEntity<>(body, header, status);
    }


    /**
     Takes a sting value and tries to turn it to a date object
     according to the DATE_FORMAT_1

     @param date takes a string to tun it into a date object
     @return returns a date object created from te parameter
     */

    public static Date getDateFromString1(String date)
    {
        Date result = null;
        try
        {
            result = DATE_FORMAT_1.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace(); // This needs to be logged
        }
        return result;
    }

    /**
     Takes a sting value and tries to turn it to a date object
     according to the DATE_FORMAT_2

     @param date takes a string to tun it into a date object
     @return returns a date object created from te parameter
     */

    public static Date getDateFromString2(String date)
    {
        Date result = null;
        try
        {
            result = DATE_FORMAT_2.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace(); // This needs to be logged
        }
        return result;
    }

}
