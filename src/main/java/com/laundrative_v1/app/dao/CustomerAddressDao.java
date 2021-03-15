package com.laundrative_v1.app.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.laundrative_v1.app.entity.CustomerAddressEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class CustomerAddressDao
{
    private Long id;
    private Long customerId;
    private String address;
    private Long receivedType;
    private Long deliveryType;

    public CustomerAddressDao(CustomerAddressEntity entity)
    {
        this.id = entity.getId();
        this.customerId = entity.getCustomerId();
        this.address = entity.getAddress();
        this.receivedType = entity.getReceiveType();
        this.deliveryType = entity.getDeliverType();
    }
}
