package com.laundrative_v1.app.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laundrative_v1.app.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class UserDao
{
    private Long id;
    private Long institutionId;
    private String email;
    private String username;
    private String password;
    private Long role;

    // Copy constructor

    public UserDao(UserEntity entity)
    {
        this.id = entity.getId();
        this.institutionId = entity.getInstitutionId();
        this.email = entity.getEmail();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.role = entity.getRole();
    }
}
