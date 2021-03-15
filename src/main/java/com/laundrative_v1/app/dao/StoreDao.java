package com.laundrative_v1.app.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laundrative_v1.app.entity.StoreEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class StoreDao
{
        private Long id;
        private String storeName;
        private String telephone;
        private String supervisorName;
        private String email;

    public StoreDao(StoreEntity entity)
    {
        this.id = entity.getId();
        this.storeName = entity.getStoreName();
        this.telephone = entity.getTelephone();
        this.supervisorName = entity.getSupervisorName();
        this.email = entity.getEmail();
    }


}
