package com.laundrative_v2.app.beans.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "siparis")
@Getter
@Setter
@ToString
public class OrderDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "musteri_id")
    private CustomerDb customer;

    @Column(name = "siparis_tarihi")
    private Date orderDate;
    @Column(name = "siparis_yeri")
    private Integer orderLocation;

    @Column(name = "tutar")
    private BigDecimal total;
    @Column(name = "nakit")
    private BigDecimal cash;
    @Column(name = "pos")
    private BigDecimal pos;
    @Column(name = "notlar")
    private String notes;
    @Column(name = "indirim")
    private BigDecimal discount;
    @Column(name = "indirim_tipi")
    private Integer discountType;
    @Column(name = "teslim_alma_tarihi")
    private Date receivingDate;
    @Column(name = "teslim_alma_adres")
    private String receivingAddress;
    @Column(name = "teslim_etme_tarihi")
    private Date deliveryDate;
    @Column(name = "teslim_etme_adres")
    private String deliveryAddress;
    @Column(name = "teslimat_status")
    private int deliveryStatus;
    @Column(name = "odeme_status")
    private int paymentStatus;
}