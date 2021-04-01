package com.laundrative_v2.app.beans.db.orderDb;

import com.laundrative_v2.app.beans.json.order.OrderProduct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "siparis_detay")
@Getter
@Setter
@ToString
public class OrderDetailsDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "siparis_id")
    private Long orderId;

    @Column(name = "kategori")
    private Long categoryId;
    @Column(name = "cins")
    private Long kindId;
    @Column(name = "tip")
    private Long type;
    @Column(name = "adet")
    private Integer amount;
    @Column(name = "tarih")
    private Date date;
    @Column(name = "fiyat")
    private BigDecimal price;
    @Column(name = "durum")
    private Integer status;

    public OrderDetailsDb() {}

    public static OrderDetailsDb from(Long orderId, OrderProduct product)
    {
        OrderDetailsDb orderDetailsDb = new OrderDetailsDb();

        orderDetailsDb.setOrderId(orderId);
        orderDetailsDb.setCategoryId(product.getCategoryId());
        orderDetailsDb.setKindId(product.getKindId());
        orderDetailsDb.setType(product.getType());
        orderDetailsDb.setAmount(product.getAmount());
        orderDetailsDb.setDate(null);
        orderDetailsDb.setPrice(product.getPrice());
        orderDetailsDb.setStatus(0);

        return orderDetailsDb;
    }
}
