package com.laundrative_v2.app.beans.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "musteri_adresler")
@Getter
@Setter
@ToString
public class CustomerAddressDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "musteri_id")
    private CustomerDb customer;

    @Column(name = "adres")
    private String address;
    @Column(name = "teslim_alma")
    private int receiving;
    @Column(name = "teslim_etme")
    private int handingOver;

    public CustomerAddressDb(){}
}
