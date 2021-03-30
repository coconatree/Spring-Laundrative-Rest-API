package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.json.Request.OrderPostReq;
import com.laundrative_v2.app.dao.OrderDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    OrderDao orderDao;

    @PostMapping(value = "/")
    public ResponseEntity<Object> set(@RequestBody OrderPostReq req)
    {
        Long createdId = orderDao.save(req);

        if(createdId != null)
            return Utility.createResponse("", createdId, HttpStatus.OK);
        else
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
    }
}
