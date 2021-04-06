package com.laundrative_v2.app.service;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.db.customerDb.CustomerDeletedDb;
import com.laundrative_v2.app.beans.json.address.request.AddressAddReq;
import com.laundrative_v2.app.beans.json.customer.AddressObject;
import com.laundrative_v2.app.beans.json.customer.request.CustomerDelReq;
import com.laundrative_v2.app.beans.json.customer.request.CustomerPostReq;
import com.laundrative_v2.app.beans.json.customer.response.CustomerInfoRes;
import com.laundrative_v2.app.dao.CustomerDao;
import com.laundrative_v2.app.exception.CustomerDoesntOwnTheAddress;
import com.laundrative_v2.app.exception.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService
{
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AddressService addressService;


    public CustomerInfoRes info(String email)
    {
        return CustomerInfoRes.from(findCustomerByEmail(email));
    }

    public CustomerDb findCustomerByEmail(String email) throws EmailNotFoundException
    {
        CustomerDb customer = customerDao.findByEmail(email);

        if(customer == null)
            throw new EmailNotFoundException("Authentication Error", HttpStatus.UNAUTHORIZED);
        else
            return customer;
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

    public void deleteCustomer(String email, CustomerDelReq requestBody)
    {
        CustomerDb originalCustomer = findCustomerByEmail(email);
        CustomerDeletedDb deletedCustomer = CustomerDeletedDb.from(originalCustomer, requestBody);

        customerDao.saveToDeleted(deletedCustomer);
        customerDao.delete(originalCustomer);
    }

    public Long saveAddress(String email, AddressAddReq address)
    {
        return addressService.saveCustomerAddress(findCustomerByEmail(email).getId(), address);
    }

    public void updateAddress(String email, Long addressId, AddressAddReq address)
    {
        validate(email, addressId);
        addressService.updateCustomerAddress(addressId, address);
    }

    public void deleteAddress(String email, Long addressId)
    {
        validate(email, addressId);
        addressService.deleteCustomerAddress(addressId);
    }

    public List<AddressObject> getAllAddresses(String email)
    {
        return addressService.getAllCustomerAddresses(findCustomerByEmail(email));
    }

    private void validate(String email, Long addressId)
    {
        if(!addressService.owns(findCustomerByEmail(email).getId(), addressId))
        {
            throw new CustomerDoesntOwnTheAddress("Authentication Failed", HttpStatus.UNAUTHORIZED);
        }
    }

}
