package com.laundrative_v1.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.laundrative_v1.app.entity.TestM;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/test")
public class TestController
{
    @GetMapping(value = "/")
    public String test0()
    {
        return "YOUR REQUEST HAS BEEN RECEIVED";
    }

    @GetMapping(value = "/{id}")
    public String test1(@PathVariable(value = "id") long id)
    {
        return String.format("REQUEST WITH ID : %d", id);
    }

    @GetMapping(value = "/response")
    public ResponseEntity<String> test2()
    {
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String test3(@RequestBody TestM mdl, HttpServletRequest req) throws JsonProcessingException
    {
        return mdl.toString();
    }
}
