package com.laundrative_v2.app.security;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.customer.CustomerDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Setter
@Getter
@ToString
public class CustomerPrincipal implements UserDetails
{
    private CustomerDb user;

    public CustomerPrincipal(CustomerDb customer)
    {
        this.user = customer;
    }

    public static CustomerPrincipal from(CustomerDb db)
    {
        CustomerPrincipal details = new CustomerPrincipal(db);
        return details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.emptyList();
    }

    @Override
    public String getPassword()
    {
        return this.user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return user.isActive();
    }
}
