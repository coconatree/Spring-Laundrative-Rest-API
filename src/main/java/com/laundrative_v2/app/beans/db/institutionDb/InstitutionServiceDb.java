package com.laundrative_v2.app.beans.db.institutionDb;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "kurum_hizmet")
@Getter
@Setter
@ToString
public class InstitutionServiceDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kurum_id")
    private Long institutionId;

    @Column(name = "mahalle_id")
    public Long neighborhoodId;

    @Column(name  = "min_sip_tutar")
    private BigDecimal minOrderAmount;
    @Column(name  = "min_servis_tutar")
    private BigDecimal minServiceAmount;

    protected InstitutionServiceDb(){}
}
