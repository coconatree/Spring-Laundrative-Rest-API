package com.laundrative_v2.app.exception;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends BaseException
{
    public EmailNotFoundException(String message, HttpStatus status)
    {
        super(message, status);
    }
}
