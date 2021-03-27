package com.laundrative_v2.app.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import static com.laundrative_v2.app.util.UtilityConfig.*;

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

    public String imageToHex(String filename)
    {
        try
        {
            // Creating a byte array and a ByteArrayStream

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(this.getImage(filename), "png", output);

            byte[] bytes = output.toByteArray();

            // Converting to hexadecimal and returning it as a string

            return DatatypeConverter.printHexBinary(output.toByteArray());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args)
    {
        Utility utility = Utility.getInstance();

        String value = utility.imageToHex("k1_1_01.png");

        if(value == null)
            System.out.println("There was a problem");
        else
            System.out.println(value);
    }
}
