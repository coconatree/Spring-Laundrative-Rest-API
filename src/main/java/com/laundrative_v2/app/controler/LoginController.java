package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.dao.LoginDao;
import com.laundrative_v2.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController
{
    @Autowired
    LoginDao service;

    @GetMapping(value = "/{email}/{password}")
    public ResponseEntity<Object> login(@PathVariable(value = "email") String email, @PathVariable(value = "password") String password)
    {
        String TOKEN = service.login(email, password);

        if(TOKEN == null)
            return Utility.createResponse(null, "Please use a valid email and password", HttpStatus.UNAUTHORIZED);
        else
            return Utility.createResponse(TOKEN, TOKEN, HttpStatus.ACCEPTED);
    }
}
