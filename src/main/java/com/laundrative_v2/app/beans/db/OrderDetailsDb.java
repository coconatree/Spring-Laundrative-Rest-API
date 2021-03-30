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
}
