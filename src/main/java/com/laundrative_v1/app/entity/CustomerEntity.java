package com.laundrative_v1.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "musteri")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "adi")
    private String name;
    @Column(name = "telefon")
    private String telephone;
    @Column(name = "email")
    private String email;
    @Column(name = "olusturma_tarih")
    private Date creationDate;
    @Column(name = "guncellenme_tarih")
    private Date updateDate;
    @Column(name = "kurum_id")
    private Long institutionId;
    @Column(name = "musteri_notu")
    private String customerNote;
    @Column(name = "indirim_tipi")
    private Long discountType;
    @Column(name = "indirim_orani")
    private Long discountPercentage;


    protected CustomerEntity() {}
}
