package com.laundrative_v2.app.beans.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ilceler")
@Getter
@Setter
@ToString
public class DistrictDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key ?

    @Column(name = "il_id")
    private Long provinceId;

    @Column(name = "ilce_adi")
    private String districtName;

    protected DistrictDb(){}
}