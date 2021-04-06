package com.laundrative_v2.app.beans.json.order.response;

import com.laundrative_v2.app.beans.db.orderDb.OrderDb;
import com.laundrative_v2.app.beans.db.orderDb.OrderDetailsDb;
import com.laundrative_v2.app.beans.json.order.OrderProduct;
import lombok.*;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderHistoryRes
{
    private Date orderDate;
    private Long orderId;
    private int  status;
    private BigDecimal price;
    private List<OrderProduct> productList;
    private BigDecimal serviceTotal;
    private BigDecimal servicePrice;
    private int discountType;
    private BigDecimal discountPercentage;
    private Date receivingDate;
    private Date deliverDate;
    private String receivingAddress;
    private String deliveryAddress;

    public static OrderHistoryRes from(OrderDb db, List<OrderDetailsDb> list)
    {
        OrderHistoryRes response = new OrderHistoryRes();

        response.setOrderDate(db.getOrderDate());
        response.setOrderId(db.getId());

        // response.setStatus();
        // WHICH STATUS THERE ARE MORE THAN ONE DETAILS PER ORDER

        response.setProductList(list.stream().map(element -> OrderProduct.from(element)).collect(Collectors.toList()));

        // Hizmet toplamı ??

        //TODO
        //Clean this up and ask about the unknown fields

        response.setServiceTotal(new BigDecimal(0.0));
        response.setServicePrice(db.getTotal());
        // response.setDiscountType();
        response.setDiscountPercentage(db.getDiscount());
        response.setReceivingDate(db.getReceivingDate());
        response.setDeliverDate(db.getDeliveryDate());
        response.setReceivingAddress(db.getReceivingAddress());
        response.setDeliveryAddress(db.getDeliveryAddress());

        return response;
    }
}
