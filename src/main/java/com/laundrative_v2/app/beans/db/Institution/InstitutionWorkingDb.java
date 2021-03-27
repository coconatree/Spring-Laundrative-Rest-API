package com.laundrative_v2.app.beans.db.Institution;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "kurum_calısma")
@Getter
@Setter
@ToString
public class InstitutionWorkingDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "kurum_id")
    private InstitutionDb institutionId;
    @Column(name = "gun")
    private int day;
    @Column(name  = "baslama_saati")
    private Date startingDate;
    @Column(name  = "bitis_saati")
    private Date endingDate;

    protected InstitutionWorkingDb(){}
}
