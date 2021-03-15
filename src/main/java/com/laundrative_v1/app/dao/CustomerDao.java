package com.laundrative_v1.app.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laundrative_v1.app.entity.CustomerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class CustomerDao
{
    private Long id;
    private Long institutionId;

    private Long discountType;
    private Long discountPercentage;

    private String name;
    private String telephone;
    private String email;
    private String customerNote;

    private Date creationDate;
    private Date updateDate;

    public CustomerDao(CustomerEntity entity)
    {
        this.id = entity.getId();
        this.institutionId = entity.getInstitutionId();
        this.discountType = entity.getDiscountType();
        this.discountPercentage = entity.getDiscountPercentage();
        this.name = entity.getName();
        this.telephone = entity.getTelephone();
        this.email = entity.getEmail();
        this.customerNote = entity.getCustomerNote();
        this.creationDate = entity.getCreationDate();
        this.updateDate = entity.getUpdateDate();
    }
}
