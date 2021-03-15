package com.laundrative_v1.app.controller;

import com.laundrative_v1.app.dao.KindDao;
import com.laundrative_v1.app.dao.TypeDao;
import com.laundrative_v1.app.service.KindServiceImpl;
import com.laundrative_v1.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/kind")
public class KindController
{
    @Autowired
    KindServiceImpl service;

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get(@PathVariable(value = "id") Long id)
    {
        KindDao kindDao = service.read(id);

        if(kindDao == null)
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        else
            return Utility.createResponse("", kindDao, HttpStatus.OK);
    }

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll()
    {
        List<KindDao> list = service.readAll();

        if(list == null)
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        else
            return Utility.createResponse("", list, HttpStatus.OK);
    }

}
