package com.laundrative_v2.app.beans.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderJson
{
    private Long customerId;
    private Integer orderLocation;
    private BigDecimal total;
    private BigDecimal cash;
    private BigDecimal pos;
    private String notes;
    private Date receivingDate;
    private String receivingAddress;
    private Date deliveryDate;
}
