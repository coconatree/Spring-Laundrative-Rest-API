package com.laundrative_v2.app.beans.db;

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

    @ManyToOne
    @JoinColumn(name = "siparis_id")
    private OrderDb order;

    @Column(name = "tarih")
    private Date date;
    @Column(name = "hareket_tipi")
    private int movementType;

    public OrderMovementDb() {}
}
