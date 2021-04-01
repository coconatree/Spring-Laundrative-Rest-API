package com.laundrative_v2.app.beans.db.orderDb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "siparis_hareket")
@Getter
@Setter
@ToString
public class OrderMovementDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tarih")
    private Date date;
    @Column(name = "hareket_tipi")
    private int movementType;

    @Column(name = "siparis_id")
    private Long orderId;

    public OrderMovementDb() {}

    public static OrderMovementDb from(Long orderId, Date date, int type)
    {
        OrderMovementDb orderMovementDb = new OrderMovementDb();

        orderMovementDb.setOrderId(orderId);
        orderMovementDb.setDate(date);
        orderMovementDb.setMovementType(type);

        return orderMovementDb;
    }
}
