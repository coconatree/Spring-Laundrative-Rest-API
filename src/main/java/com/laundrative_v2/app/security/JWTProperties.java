package com.laundrative_v2.app.security;

// This will change

public class JWTProperties
{
    // @Value($())
    public static final String SECRET = "secret";  // Following will come from the database
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 5; // 5 Hours in milliseconds
    public static final String PREFIX = "Bearer ";  // Token prefix
    public static final String HEADER_NAME = "Authorization";
}
