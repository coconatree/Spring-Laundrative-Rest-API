package com.laundrative_v1.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@Entity
@Table(name = "tip")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class TypeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "adi")
    private String name;

    protected TypeEntity(){}
}
