package com.laundrative_v2.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException
{
    @Getter
    private HttpStatus status;

    public BaseException(String message, HttpStatus status)
    {
        super(message);
    }
}
