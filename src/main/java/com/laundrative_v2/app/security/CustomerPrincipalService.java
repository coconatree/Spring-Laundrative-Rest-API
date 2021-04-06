package com.laundrative_v2.app.security;

import com.laundrative_v2.app.repository.customerRepo.CustomerRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerPrincipalService implements UserDetailsService
{
    private CustomerRepo repository;

    public CustomerPrincipalService(CustomerRepo repository)
    {
        this.repository = repository;
    }

    // Overriding it so it loads the customer by email

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return CustomerPrincipal.from(this.repository.findByEmailCustom(username));
    }
}
