package com.laundrative_v2.app.beans.db.Institution;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name = "kurum_calisma")
@Getter
@Setter
@ToString
public class InstitutionWorkingDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne(cascade = CascadeType.ALL, targetEntity = InstitutionDb.class)
    @Column(name = "kurum_id")
    private Long institutionId;

    @Column(name = "gun")
    private int day;
    @Column(name  = "baslama_saati")
    private Time startingTime;
    @Column(name  = "bitis_saati")
    private Time endingTime;

    protected InstitutionWorkingDb(){}
}
