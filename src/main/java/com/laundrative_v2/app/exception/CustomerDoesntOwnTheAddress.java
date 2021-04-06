package com.laundrative_v2.app.exception;

import org.springframework.http.HttpStatus;

public class CustomerDoesntOwnTheAddress extends BaseException
{
    public CustomerDoesntOwnTheAddress(String message, HttpStatus status)
    {
        super(message, status);
    }
}
