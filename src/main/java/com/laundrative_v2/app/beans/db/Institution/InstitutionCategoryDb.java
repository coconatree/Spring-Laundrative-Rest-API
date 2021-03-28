package com.laundrative_v2.app.beans.db.Institution;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "kurum_kategori")
@Getter
@Setter
@ToString
public class InstitutionCategoryDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "kurum_id")
    private Long institutionId;
    @Column(name = "kategori_id")
    private Long categoryId;

    protected InstitutionCategoryDb(){}
}
