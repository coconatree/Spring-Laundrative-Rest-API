package com.laundrative_v2.app.beans.json.customer;


import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDetails
{
    private String id;
    private String email;
    private String password;

    public CustomerDetails(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public static CustomerDetails from(CustomerDb db)
    {
        CustomerDetails details = new CustomerDetails();

        details.setId(String.valueOf(db.getId()));
        details.setEmail(db.getEmail());
        details.setPassword(db.getPassword());

        return details;
    }
}
