package com.laundrative_v2.app.exception;

import org.springframework.http.HttpStatus;

public class CredentialFormatIsWrong extends BaseException
{
    public CredentialFormatIsWrong(String message, HttpStatus status)
    {
        super(message, status);
    }
}
