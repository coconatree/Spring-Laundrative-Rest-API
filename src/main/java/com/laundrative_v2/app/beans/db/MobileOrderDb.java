package com.laundrative_v2.app.beans.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mobile_siparis")
@Getter
@Setter
@ToString
public class MobileOrderDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tarih")
    private Date date;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "notlar")
    private String notes;

    @OneToOne
    @JoinColumn(name = "siparis_id")
    private OrderDb orderDb;
}
