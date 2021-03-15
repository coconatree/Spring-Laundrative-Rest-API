package com.laundrative_v1.app.controller;

import com.laundrative_v1.app.dao.TypeDao;
import com.laundrative_v1.app.service.TypeServiceImpl;
import com.laundrative_v1.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/type")
public class TypeController
{
    @Autowired
    TypeServiceImpl service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable(value = "id") Long id)
    {
        // TypeDao typeDao = service.read(id);

        // if(typeDao == null)
        return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        //else
        //    return Utility.createResponse("", typeDao, HttpStatus.OK);
    }

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll()
    {
        List<TypeDao> list = service.readAll();

        if(list == null)
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        else
            return Utility.createResponse("", list, HttpStatus.OK);
    }

}
