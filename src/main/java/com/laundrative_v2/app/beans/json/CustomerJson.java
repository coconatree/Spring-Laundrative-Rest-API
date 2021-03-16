package com.laundrative_v2.app.beans.json;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerJson
{
    private String name;
    private String password;
    private String telephone;
    private String email;
    private String customerNote;
}
