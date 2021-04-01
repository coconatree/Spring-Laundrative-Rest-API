package com.laundrative_v2.app.beans.json.order;

import com.laundrative_v2.app.beans.db.orderDb.OrderDetailsDb;
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

    public static OrderProduct from(OrderDetailsDb db)
    {
        OrderProduct product = new OrderProduct();

        product.setCategoryId(db.getCategoryId());
        product.setKindId(db.getKindId());
        product.setType(db.getType());
        product.setAmount(db.getAmount());
        product.setPrice(db.getPrice());

        return product;
    }
}
