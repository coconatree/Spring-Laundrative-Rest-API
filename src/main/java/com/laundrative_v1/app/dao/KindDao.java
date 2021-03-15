package com.laundrative_v1.app.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laundrative_v1.app.entity.KindEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class KindDao
{
    private Long id;
    private Long category;
    private String name;
    private String image;
    private Long storeId;
    private Long active;

    public KindDao(KindEntity entity)
    {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.name = entity.getName();
        this.image = entity.getImage();
        this.storeId = entity.getStoreId();
        this.active = entity.getActive();
    }
}
