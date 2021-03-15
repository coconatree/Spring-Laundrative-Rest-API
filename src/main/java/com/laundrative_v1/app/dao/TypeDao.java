package com.laundrative_v1.app.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laundrative_v1.app.entity.StoreEntity;
import com.laundrative_v1.app.entity.TypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class TypeDao
{
    private Long id;
    private String name;

    public TypeDao(TypeEntity entity)
    {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
