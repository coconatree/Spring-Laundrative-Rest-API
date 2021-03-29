package com.laundrative_v2.app.beans.db.Institution;


import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
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

    //@ManyToOne(cascade = CascadeType.ALL, targetEntity = InstitutionDb.class)
    //@JoinColumn(name="kurum_id")
    @Column(name = "kurum_id")
    private Long institutionId;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = NeighborhoodDb.class)
    @JoinColumn(name = "mahalle_id")
    public NeighborhoodDb neighborhoodDb;

    @Column(name  = "min_sip_tutar")
    private BigDecimal minOrderAmount;
    @Column(name  = "min_servis_tutar")
    private BigDecimal minServiceAmount;

    protected InstitutionServiceDb(){}
}
