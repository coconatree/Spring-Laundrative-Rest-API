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

/**
 @author Emre Caniklioglu
 @versio 1.0.0

 This is the controller class for the Stores it has listens to the following URL's;

    -   GET  api/v1/store/
    -   GET  api/v1/store/{id}
    -   POST api/v1/store/

 */

@RestController
@RequestMapping("api/v1/user/store")
public class StoreController
{
    @Autowired
    StoreServiceImpl service;

    /**
        Following functions listens for the api/v1/store/{id} for the GET requests and calls the store service upon
        receiving and validating the request.

        @param  id is the path variable {id} form the request link and it is used to search for a user
        @return
     */

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDao> get(@PathVariable(value = "id") Long id)
    {
        HttpHeaders header = new HttpHeaders();

        header.set("ACCESS_KEY", "");

        return new ResponseEntity<>(service.read(id), header, HttpStatus.OK);
    }

    /**
     Following functions listens for the api/v1/store/ for the GET requests and calls the store service upon
     receiving and validating the request.

     @return
     */

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoreDao>> getAll()
    {
        HttpHeaders header = new HttpHeaders();

        header.set("ACCESS_KEY", "");

        return new ResponseEntity<>(service.readAll(), header, HttpStatus.OK);
    }
}
