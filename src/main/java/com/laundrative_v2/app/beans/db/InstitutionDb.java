package com.laundrative_v2.app.beans.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "kurumlar")
@Getter
@Setter
@ToString
public class InstitutionDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "kurum_adi")
    private String name;
    @Column(name = "adress")
    private String address;
    @Column(name = "telefon")
    private String telephone;
    @Column(name = "yonetici_adi")
    private String supervisorName;
    @Column(name = "yonetici_eposta")
    private String supervisorEmail;
}
