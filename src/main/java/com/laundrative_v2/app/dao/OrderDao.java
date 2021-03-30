package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.*;
import com.laundrative_v2.app.beans.json.Request.OrderPostReq;
import com.laundrative_v2.app.beans.pojo.OrderProduct;
import com.laundrative_v2.app.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

import static com.laundrative_v2.app.configuration.UtilityConfig.DATE_FORMAT_1;

@Service
public class OrderDao
{
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    MobileOrderRepo mobileOrderRepo;

    @Autowired
    OrderMovementRepo orderMovementRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Autowired
    CustomerRepo customerRepo;

    Logger logger = LoggerFactory.getLogger(OrderDao.class);

    public Long save(OrderPostReq req)
    {
        try
        {
            OrderDb orderDb = new OrderDb();

            // UNTIL WE STAT USING JWT

            CustomerDb customerDb = customerRepo.findById(16862L).get();

            orderDb.setAccountId(1L); // DONT KNOW
            orderDb.setCustomerId(customerDb.getId());

            orderDb.setOrderDate(null);
            orderDb.setTotal(req.getTotal());
            orderDb.setCash(null);
            orderDb.setPos(null);
            orderDb.setNotes(req.getNote());
            orderDb.setDiscount(req.getDiscountPercentage());
            orderDb.setDiscountType(null);

            orderDb.setReceivingDate(req.getReceivingDate());
            orderDb.setReceivingAddress(req.getReceivingAddress());
            orderDb.setDeliveryDate(req.getDeliveryDate());
            orderDb.setDeliveryAddress(req.getDeliveryAddress());
            orderDb.setDeliveryStatus(0);

            OrderDb saved = orderRepo.save(orderDb);

            ArrayList<OrderDetailsDb> orderDetailsDbList = new ArrayList<>();
            OrderDetailsDb filler = null;

            for (OrderProduct product : req.getProducts())
            {
                filler = new OrderDetailsDb();

                filler.setOrderId(saved.getId());
                filler.setCategoryId(product.getCategoryId());
                filler.setKindId(product.getKindId());
                filler.setType(product.getType());
                filler.setAmount(product.getAmount());
                filler.setDate(null);
                filler.setPrice(product.getPrice());
                filler.setStatus(0);

                orderDetailsDbList.add(filler);
            }

            OrderMovementDb movementDb = new OrderMovementDb();


            movementDb.setDate(new Date());
            movementDb.setMovementType(0);

            MobileOrderDb mobileOrderDb = new MobileOrderDb();

            mobileOrderDb.setDate(null);
            mobileOrderDb.setName("NEEDS LOGGING SYSTEM");
            mobileOrderDb.setAddress(null); // Which address
            mobileOrderDb.setPhone("NEEDS LOGGING SYSTEM");
            mobileOrderDb.setEmail("NEEDS LOGGING SYSTEM");
            mobileOrderDb.setNotes(req.getNote());

            //TODO
            //-CHECK IF 0 IS DEFAULT FOR MOBILE STATUS
            // movementDb movementTypes ??? What are they ?



            movementDb.setOrderId(saved.getId());
            mobileOrderDb.setOrderDb(saved);

            mobileOrderRepo.save(mobileOrderDb);
            orderMovementRepo.save(movementDb);
            orderDetailsRepo.saveAll(orderDetailsDbList);


            if(saved != null)
                return saved.getId();
            else
                return null;
        }
        catch (Exception e)
        {
            logger.warn(e.getMessage());
            logger.warn(e.getCause().getMessage());

            System.out.println(e);
        }
        return null;
    }
}
