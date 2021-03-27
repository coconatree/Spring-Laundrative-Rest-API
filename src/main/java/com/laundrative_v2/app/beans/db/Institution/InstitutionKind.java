package com.laundrative_v2.app.beans.db.Institution;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "kurum_cins")
@Getter
@Setter
@ToString
public class InstitutionKind
{
    // There is an error wiht this class

    @Column(name = "cins_id")
    private Long kindId;

    @Column(name = "cins_kategori")
    private Long kindCategoryId;

    @Id
    @Column(name = "cins_kurum_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long kindInstitutionId;

    @Column(name = "kurum_id")
    private Long institutionId;

    protected InstitutionKind(){}
}