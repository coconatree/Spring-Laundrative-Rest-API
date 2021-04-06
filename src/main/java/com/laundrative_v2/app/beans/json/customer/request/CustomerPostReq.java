package com.laundrative_v2.app.beans.json.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerPostReq
{
    private String name;
    private String password;
    private String telephone;
    private String email;
    private String note;
}
