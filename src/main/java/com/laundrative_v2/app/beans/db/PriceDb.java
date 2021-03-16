package com.laundrative_v2.app.beans.db;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "fiyat")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PriceDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "kategori")
    private Long category;
    @Column(name = "cins")
    private Long kind;
    @Column(name = "tip")
    private Long type;
    @Column(name = "fiyat")
    private BigDecimal price;
    @Column(name = "kurum_id")
    private Long institutionId;

    protected PriceDb(){}
}
