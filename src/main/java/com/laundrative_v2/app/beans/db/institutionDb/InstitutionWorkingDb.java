package com.laundrative_v2.app.beans.db.institutionDb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIME)
    @Column(name  = "baslama_saati")
    private Date startingTime;

    @Temporal(TemporalType.TIME)
    @Column(name  = "bitis_saati")
    private Date endingTime;

    protected InstitutionWorkingDb(){}
}
