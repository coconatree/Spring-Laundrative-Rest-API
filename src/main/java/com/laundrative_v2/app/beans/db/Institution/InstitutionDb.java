package com.laundrative_v2.app.beans.db.Institution;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "kurum")
@Getter
@Setter
@ToString
public class InstitutionDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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


    //TODO
    // -Should probably look into cascade type for choosing the optimal one !!!


    @OneToMany(cascade = CascadeType.ALL, targetEntity = InstitutionServiceDb.class)
    private List<InstitutionServiceDb> institutionServiceList;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = InstitutionWorkingDb.class)
    private List<InstitutionWorkingDb> institutionWorkingList;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = InstitutionCategoryDb.class)
    private List<InstitutionCategoryDb> institutionCategoryList;

    protected InstitutionDb(){};
}
