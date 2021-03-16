package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.dao.TypeDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/type")
public class TypeController
{
    @Autowired
    TypeDao typeDao;

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAll()
    {
        return Utility.createResponse("", typeDao.readALl(), HttpStatus.OK);
    }
}
