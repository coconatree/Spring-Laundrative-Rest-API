package com.laundrative_v1.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "kullanicilar")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "kurum_id")
    private Long institutionId;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password; // Password should be hashed
    @Column(name = "rol")
    private Long role;
    @Column(name = "secret_key")
    private String secretKey;

    protected UserEntity(){}
}
