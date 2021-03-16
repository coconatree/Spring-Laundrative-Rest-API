package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.CustomerDb;
import com.laundrative_v2.app.beans.json.OrderJson;
import com.laundrative_v2.app.dao.OrderDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/order")
public class OrderController
{
    @Autowired
    OrderDao orderDao;

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get(@PathVariable(value = "id") Long id)
    {
        return Utility.createResponse("", orderDao.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> set(@RequestBody OrderJson obj)
    {
        return Utility.createResponse("", orderDao.save(obj), HttpStatus.OK);
    }
}
