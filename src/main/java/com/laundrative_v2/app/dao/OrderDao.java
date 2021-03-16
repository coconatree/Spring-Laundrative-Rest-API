package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.*;
import com.laundrative_v2.app.beans.json.OrderJson;
import com.laundrative_v2.app.repository.*;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderDao
{
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    @Autowired
    OrderMovementRepo orderMovementRepo;
    @Autowired
    MobileOrderRepo mobileOrderRepo;
    @Autowired
    CustomerRepo customerRepo;

    public List<OrderDb> get(Long id)
    {
        if(customerRepo.existsById(id))
        {
            return orderRepo.findByCustomer(customerRepo.findById(id).get());
        }
        return null;
    }

    public List<Object> save(OrderJson obj)
    {
        if(customerRepo.existsById(obj.getCustomerId()))
        {
            OrderDb order = new OrderDb();

            order.setCustomer(customerRepo.findById(obj.getCustomerId()).get());
            order.setOrderDate(new Date());
            order.setOrderLocation(obj.getOrderLocation());
            order.setTotal(obj.getTotal());
            order.setCash(obj.getCash());
            order.setPos(obj.getPos());
            order.setNotes(obj.getNotes());
            order.setDiscount(new BigDecimal(0));
            order.setDiscountType(0);
            order.setReceivingDate(null);
            order.setReceivingAddress(obj.getReceivingAddress());
            order.setDeliveryDate(null);
            order.setDeliveryAddress("");
            order.setDeliveryStatus(0);
            order.setPaymentStatus(0);

            OrderDetailsDb orderDetails = new OrderDetailsDb();

            orderDetails.setOrder(order);
            orderDetails.setCategory(1);
            orderDetails.setKind("");
            orderDetails.setType(1);
            orderDetails.setName("");
            orderDetails.setAmount(1);
            orderDetails.setDate(new Date());
            orderDetails.setPrice(new BigDecimal(1));
            orderDetails.setStatus(1);

            OrderMovementDb orderMovement = new OrderMovementDb();

            orderMovement.setOrder(order);
            orderMovement.setDate(new Date());
            orderMovement.setMovementType(1);

            MobileOrderDb mobileOrderDb = new MobileOrderDb();

            mobileOrderDb.setDate(new Date());
            mobileOrderDb.setName("");
            mobileOrderDb.setAddress("");
            mobileOrderDb.setPhone("");
            mobileOrderDb.setEmail("");
            mobileOrderDb.setNotes("");
            mobileOrderDb.setOrder(order);

            List<Object> list = new ArrayList<>();

            list.add(orderRepo.save(order));
            list.add(orderDetailsRepo.save(orderDetails));
            list.add(orderMovementRepo.save(orderMovement));

            return list;
        }
        return null;
    }
}
