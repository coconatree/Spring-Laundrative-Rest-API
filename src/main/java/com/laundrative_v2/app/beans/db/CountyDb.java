package com.laundrative_v2.app.beans.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "semtler")
@Getter
@Setter
@ToString
public class CountyDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key ?

    @Column(name = "ilce_id")
    private Long districtId;

    @Column(name = "semt_adi")
    private String countyName;
}
