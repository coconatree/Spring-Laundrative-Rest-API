package com.laundrative_v2.app.beans.json.customer;


import lombok.*;

import javax.persistence.Column;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerPost
{
    private String name;
    private String password;
    private String telephone;
    private String email;
    private Date updateDate;
}
