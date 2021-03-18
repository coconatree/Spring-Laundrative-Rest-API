package com.laundrative_v2.app.beans.db;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "iller")
@Getter
@Setter
@ToString
public class ProvinceDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "il_adi")
    private String provinceName;
}
