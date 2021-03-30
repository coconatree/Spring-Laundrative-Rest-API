package com.laundrative_v2.app.beans.json.Request;

import com.laundrative_v2.app.beans.pojo.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPostReq
{
    // android = 2 ios = 3
    private Long os;
    @Nullable
    private Long oldOrderNo;
    private String receivingAddress;
    private String deliveryAddress;
    private String receivingPersonName;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private Date receivingDate;
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private Date deliveryDate;

    private String note;
    private BigDecimal discountPercentage;
    private BigDecimal total;
    private OrderProduct[] products;
}
