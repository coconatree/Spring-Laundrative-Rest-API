package com.laundrative_v2.app.util;

import com.laundrative_v2.app.beans.pojo.WorkingHoursJson;
import com.laundrative_v2.app.beans.pojo.TimeDayAsNumber;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.util.*;

import static com.laundrative_v2.app.configuration.UtilityConfig.*;

public class Utility
{
    /**
     Implementing the singleton method for the class
     */

    private static Utility self = null;

    public static Utility getInstance()
    {
        if(self == null)
            self = new Utility();
        return self;
    }

    // Private base constructor

    private Utility() { }

    /**
     Following methods creates a ResponseEntity according to the given parameters

     @param key this is the JWT
     @param body this is the response object
     @param status this is the status

     @return returns a ResponseEntity
     */

    public static ResponseEntity<Object> createResponse(String key, Object body, HttpStatus status)
    {
        HttpHeaders header = new HttpHeaders();
        header.set("JWT", key);

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

    /** *********************************************************************** */

    private BufferedImage getImage(String filename)
    {
        try
        {
            // Reading the image

            URL input = getClass().getClassLoader().getResource("kindImages/" + filename);
            return ImageIO.read(input);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String imageToBase64(String filename)
    {
        try
        {
            // Creating a byte array and a ByteArrayStream

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(this.getImage(filename), "png", output);

            byte[] bytes = output.toByteArray();

            // Converting to base64 and returning it as a string

            String encodedString = Base64.getEncoder().encodeToString(bytes);

            return encodedString;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Calendar getCalendar(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static void main(String[] args)
    {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date d = cal.getTime();

        System.out.println(d);
        //Time time = new Time(131313123123L);

        //WorkingHoursJson workingHoursJson = createWorkingHoursJson(getDateFromString1("2021-03-26T17:48:04"), 2L, time, new Time(12131313L));

        //System.out.println(workingHoursJson.getStartingDate());
        //System.out.println(workingHoursJson.getEndingDate());
        /*
        String date1 = "2021-04-28T17:48:09";
        String date2 = "2021-03-27T17:48:04";
        String date3 = "2021-03-28T17:48:04";
        String date4 = "2021-03-29T17:48:04";
        String date5 = "2021-03-30T17:48:04";
        String date6 = "2021-03-31T17:48:04";
        String date7 = "2021-04-01T17:48:04";

        parseDate(date1);
        parseDate(date2);
        parseDate(date3);
        parseDate(date4);
        parseDate(date5);
        parseDate(date6);
        parseDate(date7);
    */
    }


    /**

     Utility utility = Utility.getInstance();

     String value = utility.imageToHex("k1_1_01.png");

     if(value == null)
     System.out.println("There was a problem");
     else
     System.out.println(value);

     * */

    /**
     if(dayAsInteger + increaseAmount <= upperBound)
     {
     if((dayAsInteger + increaseAmount) % 10 == (dayAsInteger + increaseAmount))
     dayAsString = "0" + (dayAsInteger + increaseAmount);
     else
     dayAsString = String.valueOf(dayAsInteger + increaseAmount);
     }
     else
     {
     dayAsString = "0" + ((dayAsInteger + increaseAmount) % 30);

     // Checking if it is a new year

     if(monthAsInteger + 1 <= 12)
     {
     if (monthAsInteger % 10 == monthAsInteger)
     {
     monthAsString += "0" + monthAsInteger;
     }
     else
     monthAsString += String.valueOf(monthAsInteger);
     }
     else
     // Increasing the year

     yearAsString = String.valueOf(Integer.valueOf(yearAsString) + 1);
     }


     */
}
