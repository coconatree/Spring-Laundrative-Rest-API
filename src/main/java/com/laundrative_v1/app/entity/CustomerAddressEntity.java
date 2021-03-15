package com.laundrative_v1.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "musteri_adresler")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerAddressEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "musteri_id")
    private Long customerId;
    @Column(name = "adres")
    private String address;
    @Column(name = "teslim_alma")
    private Long receiveType;
    @Column(name = "teslim_etme")
    private Long deliverType;


    protected CustomerAddressEntity() {}
}
