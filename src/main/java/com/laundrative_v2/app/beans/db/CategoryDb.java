package com.laundrative_v2.app.beans.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "kategori")
@Getter
@Setter
@ToString
public class CategoryDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "adi")
    private String name;

    protected CategoryDb(){}
}
