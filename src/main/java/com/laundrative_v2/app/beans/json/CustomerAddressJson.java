package com.laundrative_v2.app.beans.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressJson
{
    private Long customerId;
    private String address;
    private int receiving;
    private int handingOver;
}
