package com.laundrative_v2.app.beans.db.Address;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "adres_mahalle")
@Getter
@Setter
@ToString
public class NeighborhoodDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "semt_id")
    private Long countyId;
    @Column(name = "mahalle_adi")
    private String neighborhoodName;
    @Column(name = "posta_kodu")
    private String postalCode;
}
