package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.customer.CustomerDetails;
import com.laundrative_v2.app.repository.customerRepo.CustomerRepo;
import com.laundrative_v2.app.utility.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginDao
{
    @Autowired
    private CustomerRepo repository;

    @Autowired
    private JWTUtil utility_JWT;

    public CustomerDb findByEmail(String email)
    {
        return repository.findByEmailCustom(email);
    }

    public boolean authenticate(String token, String email, String password)
    {
        return utility_JWT.validateToken(token, new CustomerDetails(email, password));
    }

    public String login(String email, String password)
    {
        CustomerDb customer = findByEmail(email);

        if(customer != null && customer.getPassword().equals(password))
            return createToken(CustomerDetails.from(customer));
        else
            return null;
    }

    private String createToken(CustomerDetails details)
    {
        return utility_JWT.generateToken(details);
    }
}
