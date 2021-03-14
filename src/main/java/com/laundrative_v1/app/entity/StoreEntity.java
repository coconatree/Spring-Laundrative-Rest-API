package com.laundrative_v1.app.entity;


import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;

@Repository
@Entity
@Table(name = "\"kurumlar\"")
@Getter
@Setter
@AllArgsConstructor
public class StoreEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "kurum_adi")
    private Long storeName;
    @Column(name = "telefon")
    private String telephone;
    @Column(name = "yonetici_adi")
    private String supervisorName;
    @Column(name = "yonetici_eposta")
    private String email;

    protected StoreEntity(){}
}
