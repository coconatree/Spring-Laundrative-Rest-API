package com.laundrative_v1.app.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laundrative_v1.app.entity.RoleEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class RoleDao
{
    private Long id;
    private String name;

    public RoleDao(RoleEntity entity)
    {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
