package com.laundrative_v2.app.service;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.customer.request.CustomerPostReq;
import com.laundrative_v2.app.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService
{
    @Autowired
    CustomerDao customerDao;

    public CustomerDb findCustomerByEmail(String email)
    {
        return customerDao.findByEmail(email);
    }

    public List<Long> getFavoriteInstitutionIds(String email)
    {
        if(email == null)
            return Collections.emptyList();
        return customerDao.getFavoriteList(email);
    }

    public Long registerCustomer(CustomerPostReq request)
    {
        return customerDao.register(CustomerDb.from(request));
    }

    public void updateCustomer(CustomerPostReq request)
    {
        CustomerDb customer = findCustomerByEmail(request.getEmail());
        customerDao.update(CustomerDb.updateFrom(request, customer, customer.getId()));
    }

    public void deleteCustomer(String email)
    {
        //
    }

    public void saveAddress(){}
    public void updateAddress(){}
    public void deleteAddress(){}

    public void gatAllAddresses(){}
}
