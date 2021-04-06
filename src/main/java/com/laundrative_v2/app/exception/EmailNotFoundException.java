package com.laundrative_v2.app.exception;

public class EmailNotFoundException extends RuntimeException
{
    private String message;

    public EmailNotFoundException(String message)
    {
        super(message);
    }
}
