package com.laundrative_v2.app.exception;

import com.laundrative_v2.app.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//TODO
// doesn't work properly

@ControllerAdvice
public class GeneralExceptionHandler
{
    private static Logger LOGGER = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Object> handleExceptionBase(BaseException e)
    {
        LOGGER.warn("Error : " + e.getMessage());
        LOGGER.warn("Details : " + e.getCause());

        e.printStackTrace();

        return Utility.createResponse("", e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    protected ResponseEntity<Object> handleExceptionGeneral(BaseException e)
    {
        LOGGER.warn("Error : " + e.getMessage());
        LOGGER.warn("Details : " + e.getCause());

        e.printStackTrace();

        return Utility.createResponse("", e.getMessage(), e.getStatus());
    }
}