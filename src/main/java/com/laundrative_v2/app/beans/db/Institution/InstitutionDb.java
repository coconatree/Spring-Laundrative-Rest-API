package com.laundrative_v2.app.beans.db.Institution;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "kurum")
@Getter
@Setter
@ToString
public class InstitutionDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "kurum_adi")
    private String institutionName;
    @Column(name = "adres")
    private String adres;
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "yonetici_adi")
    private String administratorName;
    @Column(name = "yonetici_eposta")
    private String administratorEmail;

    protected InstitutionDb(){};
}
