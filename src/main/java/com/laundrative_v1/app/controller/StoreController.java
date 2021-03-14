package com.laundrative_v1.app.controller;

import com.laundrative_v1.app.entity.StoreEntity;
import com.laundrative_v1.app.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/store")
public class StoreController
{
    @Autowired
    StoreService service;

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<StoreEntity> getAll()
    {
        return service.readAll();
    }
}
