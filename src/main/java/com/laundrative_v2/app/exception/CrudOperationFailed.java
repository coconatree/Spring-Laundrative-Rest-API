package com.laundrative_v2.app.exception;

import org.springframework.http.HttpStatus;

public class CrudOperationFailed extends BaseException
{
    public CrudOperationFailed(String message, HttpStatus status)
    {
        super(message, status);
    }
}
