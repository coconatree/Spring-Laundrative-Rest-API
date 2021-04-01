package com.laundrative_v2.app.beans.db.addressDb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "adres_ilce")
@Getter
@Setter
@ToString
public class DistrictDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "il_id")
    private Long provinceId;

    @Column(name = "ilce_adi")
    private String districtName;

    protected DistrictDb(){}
}