package com.laundrative_v1.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rol")
@Getter
@Setter
@AllArgsConstructor
public class RoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "adi")
    private String name;

    protected RoleEntity() { }
}
