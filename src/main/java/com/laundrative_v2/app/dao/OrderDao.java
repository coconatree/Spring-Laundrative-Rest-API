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

    public void getHistory(){}
    public void saveOrder(){}
    public void updateOrder(){}
    public void deleteOrder(){}

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
        else {
            OrderDb orderDb = OrderDb.from(request, 16862L);

            Long id = orderRepo.save(orderDb).getId();

            orderMovementRepo.save(OrderMovementDb.from(id, null, 0));

            Arrays.stream(request.getProducts())
                    .forEach(element -> orderDetailsRepo.save(OrderDetailsDb.from(id, element))
                    );
            mobileOrderRepo.save(MobileOrderDb.from(id, request));

            return id;
        }
    }

    public List<OrderHistoryRes> get_Order_History(Long customerId)
    {
        List<OrderDb> customerPastOrderIds = orderRepo.findAllByCustomerId(customerId, 1);
        return customerPastOrderIds.stream()
                .map(element -> OrderHistoryRes.from(element, orderDetailsRepo.findAllByOrderId(element.getId())))
                .collect(Collectors.toList());
    }

    public boolean delete(Long id)
    {
        OrderDb orderDb = orderRepo.findById(id).get();

        if(orderDb != null && orderDb.isActive())
        {
            orderDb.setActive(0);
            orderRepo.save(orderDb);
            return true;
        }
        else
            return false;
    }
}
