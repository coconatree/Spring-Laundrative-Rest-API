package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.json.order.request.OrderPostReq;
import com.laundrative_v2.app.beans.json.order.response.OrderHistoryRes;
import com.laundrative_v2.app.dao.OrderDao;
import com.laundrative_v2.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<Object> getPastOrders(@PathVariable(value = "customerId") Long customerId)
    {
        List<OrderHistoryRes> orderHistoryRes = orderDao.get_Order_History(customerId);

        if(orderHistoryRes != null)
            return Utility.createResponse("", orderHistoryRes, HttpStatus.OK);
        else
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long id)
    {
        boolean valid = orderDao.delete(id);

        if(valid)
            return Utility.createResponse("", "", HttpStatus.OK);
        else
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
    }
}
