package com.laundrative_v2.app.beans.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserJson
{
    private String name;
    private String surname;
    private String email;
    private String hashedPassword;
    private String telephone;
}
