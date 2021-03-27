package com.laundrative_v2.app.beans.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryJson
{
    private Long orderId;
    private Date orderDate;
    private Integer orderStatus;
    private Integer orderPrice;

    // There will be an arraylist of object type of
    // - category
    // - kind
    // - image
    // - amount
    // - price
    // - type

    // Not sure about this one ?

    private Integer totalServiceCost;

    // -------------------------

    private Integer serviceCost;
    private Integer discountType;
    private Integer discountAmount;
    private Date receivingDate;
    private Date deliveryDate;
    private String receivingAddress;
    private String deliveryAddress;
}
