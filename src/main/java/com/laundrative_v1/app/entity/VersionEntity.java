package com.laundrative_v1.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Date;

@Repository
@Entity
@Table(name = "version")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class VersionEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "aciklama")
    private String explanation;
    @Column(name = "tarih")
    private Date date;

    public VersionEntity(){}
}
