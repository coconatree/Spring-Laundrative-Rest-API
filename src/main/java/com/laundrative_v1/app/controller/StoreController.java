package com.laundrative_v1.app.controller;

import com.laundrative_v1.app.dao.StoreDao;
import com.laundrative_v1.app.service.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/store")
public class StoreController
{
    @Autowired
    StoreServiceImpl service;

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDao> get(@PathVariable(value = "id") Long id)
    {
        HttpHeaders header = new HttpHeaders();

        header.set("ACCESS_KEY", "");

        return new ResponseEntity<>(service.read(id), header, HttpStatus.OK);
    }

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoreDao>> getAll()
    {
        HttpHeaders header = new HttpHeaders();

        header.set("ACCESS_KEY", "");

        return new ResponseEntity<>(service.readAll(), header, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDao> set(@RequestBody StoreDao obj)
    {
        HttpHeaders header = new HttpHeaders();

        header.set("ACCESS_KEY", "");

        return new ResponseEntity<>(service.create(obj), header, HttpStatus.OK);
    }
}
