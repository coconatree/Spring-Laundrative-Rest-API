package com.laundrative_v2.app.beans.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct
{
    private Long categoryId;
    private Long kindId;
    private Long type;
    private Integer amount;
    private BigDecimal price;
}
