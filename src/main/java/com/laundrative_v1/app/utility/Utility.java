package com.laundrative_v1.app.utility;

// Imports from the spring framework

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.ServerResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**

 @author Emre Caniklioglu
 @date
 @version

 */

public class Utility
{
    // Singleton design pattern implementation

    private static Utility util = null;

    public static Utility getInstance()
    {
        // This will be change to use Beans from the spring framework

        if (util == null)
        {
            util = new Utility();
        }
        return util;
    }

    // Static variables for the utility class

    // Static variables about te date time

    private final static DateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private final static DateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd");
    private final static ZoneId ZONE_ID_TR = ZoneId.of("+03:00");
    private final static DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterFactory("dd.MM.yyyy HH:mm")
            .createDateTimeFormatter();

    // Static functions about the date time

    /**
    Takes a date object and formats it according to the TR time zone ad returns it

     @param date takes a Date object
     @return returns a formatted date object
     */

    public static String getFormattedDate(Date date)
    {
        ZonedDateTime time = date.toInstant().atZone(ZONE_ID_TR);
        return time.format(DATE_TIME_FORMATTER);
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

    // Static functions about the strings

    /**
     Takes a string and a character and removes all of the characters from the give string

     @param s takes a string
     @param c takes the character to be removed from the string
     @return returns a new string without the declared characters
     */

    public static String remove(String s, char c)
    {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray())
        {
            if (ch != c)
            {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // Static functions about the json objects
    /***

    public static String createJson(KeyValue... attr)
    {
        JsonObject json = new JsonObject();
        for (KeyValue entry : attr)
        {
            json.addProperty(entry.getKey(), entry.getValue());
        }
        return json.toString();
    }
    */

    public static ResponseEntity<Object> createResponse(String key, Object body, HttpStatus status)
    {
        HttpHeaders header = new HttpHeaders();
        header.set("ACCESS_KEY", key);

        return new ResponseEntity<>(body, header, status);
    }

    // Driver code for quick testing

    public static void main(String[] args)
    {
        String example = " - 0 - ";
        System.out.println(Utility.remove(example, '0'));
    }
}
