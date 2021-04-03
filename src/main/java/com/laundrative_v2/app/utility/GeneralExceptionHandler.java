package com.laundrative_v2.app.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Error> handleException(Exception e)
    {
        CustomError customError = new CustomError();
        customError.setErrorMessage(e.getMessage());

        //TODO
        // followings will be removed in the final version

        logger.warn("Error Message : " + e.getMessage());
        logger.warn("Error Cause : " + e.getCause());
        logger.warn("Class : " + e.getClass());
        logger.warn("Stack Trace : ");
        e.printStackTrace();

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}