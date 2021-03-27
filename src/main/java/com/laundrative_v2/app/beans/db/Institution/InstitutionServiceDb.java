package com.laundrative_v2.app.beans.db.Institution;


import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.OrderDb;
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

    @OneToOne
    @JoinColumn(name = "kurum_id")
    private InstitutionDb institutionId;

    @Column(name = "mahalle_id")
    private Long neighborhoodId;
    @Column(name  = "min_sip_tutar")
    private BigDecimal minOrderAmount;
    @Column(name  = "min_servis_tutar")
    private BigDecimal minServiceAmount;

    protected InstitutionServiceDb(){}
}
