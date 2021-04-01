package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.orderDb.MobileOrderDb;
import com.laundrative_v2.app.beans.db.orderDb.OrderDb;
import com.laundrative_v2.app.beans.db.orderDb.OrderDetailsDb;
import com.laundrative_v2.app.beans.db.orderDb.OrderMovementDb;
import com.laundrative_v2.app.beans.json.order.request.OrderPostReq;
import com.laundrative_v2.app.beans.json.order.response.OrderHistoryRes;
import com.laundrative_v2.app.repository.orderRepo.MobileOrderRepo;
import com.laundrative_v2.app.repository.orderRepo.OrderDetailsRepo;
import com.laundrative_v2.app.repository.orderRepo.OrderMovementRepo;
import com.laundrative_v2.app.repository.orderRepo.OrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderDao
{
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderMovementRepo orderMovementRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Autowired
    MobileOrderRepo mobileOrderRepo;

    Logger logger = LoggerFactory.getLogger(OrderDao.class);

    public Long save(OrderPostReq request)
    {
        if (request.getOldOrderId() != null)
        {
            Long oldId = request.getOldOrderId();

            OrderDb orderDb = OrderDb.from(request, 16862L);
            orderDb.setId(oldId);

            orderRepo.save(orderDb);

            orderMovementRepo.save(OrderMovementDb.from(oldId, null, 0));

            Arrays.stream(request.getProducts())
                    .forEach(element -> orderDetailsRepo.save(OrderDetailsDb.from(oldId, element))
                    );
            mobileOrderRepo.save(MobileOrderDb.from(oldId, request));

            return oldId;
        }
        else if(request.getOldOrderId() == null)
        {
            OrderDb orderDb = OrderDb.from(request, 16862L);

            Long id = orderRepo.save(orderDb).getId();

            orderMovementRepo.save(OrderMovementDb.from(id, null, 0));

            Arrays.stream(request.getProducts())
                    .forEach(element -> orderDetailsRepo.save(OrderDetailsDb.from(id, element))
                    );
            mobileOrderRepo.save(MobileOrderDb.from(id, request));

            return id;
        }
        return null;
    }

    /**

     orderRepo.updateCustom(
     oldId,
     request.getTotal(),
     request.getNote(),
     request.getDiscountPercentage(),
     request.getReceivingDate(),
     request.getReceivingAddress(),
     request.getDeliveryDate(),
     request.getDeliveryAddress()
     );

     orderMovementRepo.updateCustom(
     oldId,
     null,
     0
     );

     mobileOrderRepo.updateCustom(
     oldId,
     request.getNote()
     );

     // ORDER DETAILS WILL BE ADDED HERE

     * */

    public List<OrderHistoryRes> get_Order_History(Long id)
    {
        List<OrderDetailsDb> list = orderDetailsRepo.findByIdCustomObject(id);

        return orderRepo.findAllCustom(id).stream()
            .map(element -> OrderHistoryRes.from(element, list))
            .collect(Collectors.toList());
    }

    public boolean delete(Long id)
    {
        OrderDb orderDb = orderRepo.findById(id).get();

        orderDetailsRepo.deleteOrder(id);
        orderMovementRepo.deleteOrder(id);
        mobileOrderRepo.deleteOrder(id);
        orderRepo.deleteOrder(id);

        if(orderDb != null)
            return true;
        else
            return false;
    }
}

/**


 // UNTIL WE START USING JWT



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


 * */