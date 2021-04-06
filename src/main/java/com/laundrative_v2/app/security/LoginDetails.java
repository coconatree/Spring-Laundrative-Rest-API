package com.laundrative_v2.app.security;


import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDetails
{
    private String email;
    private String password;
}
