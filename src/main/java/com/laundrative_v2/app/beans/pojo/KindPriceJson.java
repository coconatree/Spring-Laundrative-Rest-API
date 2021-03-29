package com.laundrative_v2.app.beans.pojo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class KindPriceJson
{
    private Long kindId;
    private String kindName;
    private String kindImage;
    private Long type;
    private BigDecimal price;
}
