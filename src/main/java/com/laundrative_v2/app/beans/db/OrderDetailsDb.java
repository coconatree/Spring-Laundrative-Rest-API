package com.laundrative_v2.app.beans.db;

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

    @ManyToOne
    @JoinColumn(name = "siparis_id")
    private OrderDb order;

    @Column(name = "kategori")
    private int category;
    @Column(name = "cins")
    private String kind;
    @Column(name = "tip")
    private int type;
    @Column(name = "adi")
    private String name;
    @Column(name = "adet")
    private int amount;
    @Column(name = "tarih")
    private Date date;
    @Column(name = "fiyat")
    private BigDecimal price;
    @Column(name = "durum")
    private int status;

    public OrderDetailsDb() {}
}
