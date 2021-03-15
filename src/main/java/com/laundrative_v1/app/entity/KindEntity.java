package com.laundrative_v1.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cins")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class KindEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "kategori")
    private Long category;
    @Column(name = "adi")
    private String name;
    @Column(name = "resim")
    private String image;
    @Column(name = "kurum_id")
    private Long storeId;
    @Column(name = "aktif")
    private Long active;

    protected KindEntity(){}
}
