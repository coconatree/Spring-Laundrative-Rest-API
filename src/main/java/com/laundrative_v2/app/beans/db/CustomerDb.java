package com.laundrative_v2.app.beans.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "musteri")
@Getter
@Setter
@ToString
@JsonIgnoreProperties
public class CustomerDb
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
    @Column(name = "musteri_notu")
    private String customerNote;
    @Column(name = "indirim_tipi")
    private Integer discountType;
    @Column(name = "indirim_orani")
    private Integer discountPercentage;
    @Column(name = "password")
    private String password;

    public CustomerDb() {}
}
