package com.laundrative_v2.app.beans.db;

import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "musteri_adres")
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

    @OneToOne
    @JoinColumn(name = "mahalle_id")
    private NeighborhoodDb neighborhood;

    @Column(name = "adres")
    private String address;
    @Column(name = "teslim_alma")
    private int receiving;
    @Column(name = "teslim_etme")
    private int handingOver;

    public CustomerAddressDb(){}
}
