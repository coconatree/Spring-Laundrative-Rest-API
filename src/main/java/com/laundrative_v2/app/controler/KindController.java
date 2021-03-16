package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.dao.KindDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.CORBA.Util;

@RestController
@RequestMapping("/user/kind")
public class KindController
{
    @Autowired
    KindDao kindDao;

    @GetMapping(value = "/{id}/{category}")
    public ResponseEntity<Object> get(@PathVariable(value = "id") Long id, @PathVariable(value = "category") Long category)
    {
        KindDb kindDb = kindDao.read(id, category);

        if(kindDb == null)
        {
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        }
        return Utility.createResponse("", kindDb, HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAll()
    {
        return Utility.createResponse("", kindDao.readAll(), HttpStatus.OK);
    }
}
