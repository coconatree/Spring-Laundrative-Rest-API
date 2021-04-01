package com.laundrative_v2.app.beans.db.institutionDb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(cascade = CascadeType.ALL, targetEntity = InstitutionServiceDb.class)
    @JoinColumn(name = "kurum_id", referencedColumnName = "id")
    private List<InstitutionServiceDb> institutionServiceList;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = InstitutionWorkingDb.class)
    @JoinColumn(name = "kurum_id", referencedColumnName = "id")
    private List<InstitutionWorkingDb> institutionWorkingList;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = InstitutionCategoryDb.class)
    @JoinColumn(name = "kurum_id", referencedColumnName = "id")
    private List<InstitutionCategoryDb> institutionCategoryList;

    public boolean containsCategories(Long [] categories)
    {
        ArrayList<Boolean> validList = new ArrayList<>();

        for (Long category : categories)
        {
            if(institutionCategoryList.stream().filter(e -> e.getCategoryId() == category).collect(Collectors.toList()).size() == 1);
            {
                validList.add(true);
            }
        }
        return validList.size() == categories.length;
    }

    public boolean is_Open_In_The_Given_Day(int [] days)
    {
        ArrayList<Boolean> validList = new ArrayList<>();

        for (int day : days)
        {
            if(institutionWorkingList.stream().filter(e -> e.getDay() == day).collect(Collectors.toList()).size() == 1);
            {
                validList.add(true);
            }
        }
        return validList.size() == days.length;
    }

    public boolean has_Free_Service_For_The_Neighborhood(Long neighborhoodId)
    {
        return true;
    }
}
