package com.laundrative_v2.app.beans.json.customer.response;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CustomerInfoRes
{
    private String name;
    private String email;
    private String telephone;
    private Date creationDate;

    public static CustomerInfoRes from(CustomerDb db)
    {
        CustomerInfoRes response = new CustomerInfoRes();

        response.setName(db.getName());
        response.setEmail(db.getEmail());
        response.setTelephone(db.getTelephone());
        response.setCreationDate(db.getCreationDate());

        return response;
    }
}
