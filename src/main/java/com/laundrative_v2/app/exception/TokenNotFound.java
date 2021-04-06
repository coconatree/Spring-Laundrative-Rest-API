package com.laundrative_v2.app.exception;

import org.springframework.http.HttpStatus;

public class TokenNotFound extends BaseException
{
    public TokenNotFound(String message, HttpStatus status)
    {
        super(message, status);
    }
}
