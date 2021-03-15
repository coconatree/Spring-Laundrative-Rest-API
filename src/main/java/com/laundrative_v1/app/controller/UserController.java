package com.laundrative_v1.app.controller;

import com.laundrative_v1.app.dao.UserDao;
import com.laundrative_v1.app.service.UserServiceImpl;
import com.laundrative_v1.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController
{
    @Autowired
    UserServiceImpl service;

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get(@PathVariable(value = "id") Long id)
    {
        UserDao userDao = service.read(id);

        if(userDao == null)
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        else
            return Utility.createResponse("", userDao, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> set(@RequestBody UserDao obj)
    {
        // What happens if there is an error with the jackson object mapper ???

        UserDao userDao = service.create(obj);

        if(userDao == null)
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        else
            return Utility.createResponse("", userDao, HttpStatus.OK);
    }
}
