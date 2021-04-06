package com.laundrative_v2.app.utility;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.*;

public class Utility
{
    // Utility logger

    private static Logger logger = LoggerFactory.getLogger(Utility.class);

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

     @return returns a ResponseEntity
     */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static ResponseEntity<Object> createResponse(String key, Object body, HttpStatus status)
    {
        ResponseEntityCustom info = ResponseEntityCustom.from(
                getCurrentDate().toString(),
                HttpMethod.GET,
                status,
                body
        );

        return new ResponseEntity<>(info, null, status);
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
}
