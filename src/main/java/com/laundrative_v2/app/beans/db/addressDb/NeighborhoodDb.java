package com.laundrative_v2.app.beans.db.addressDb;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionServiceDb;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "adres_mahalle")
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL, targetEntity = InstitutionServiceDb.class,orphanRemoval = false)
    @JoinColumn(name = "mahalle_id", referencedColumnName = "id")
    private List<InstitutionServiceDb> services;

    protected NeighborhoodDb(){}
}
