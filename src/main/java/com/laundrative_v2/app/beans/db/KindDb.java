package com.laundrative_v2.app.beans.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cins")
@Getter
@Setter
@ToString
public class KindDb
{
    @Id
    private Long id;
    @Column(name = "kategori")
    private Long category;
    @Column(name = "adi")
    private String name;
    @Column(name = "resim")
    private String image;   // Will be a byte[]
    @Column(name = "aktif")
    private int active;

    protected KindDb(){}
}
