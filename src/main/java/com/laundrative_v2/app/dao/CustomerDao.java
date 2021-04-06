package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.customerDb.CustomerAddressDb;
import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.customer.AddressObject;
import com.laundrative_v2.app.beans.json.customer.request.CustomerPostReq;
import com.laundrative_v2.app.beans.json.customer.response.CustomerInfoRes;
import com.laundrative_v2.app.repository.customerRepo.CustomerAddressRepo;
import com.laundrative_v2.app.repository.customerRepo.CustomerRepo;
import com.laundrative_v2.app.exception.EmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerDao
{
    @Autowired
    private CustomerRepo repository;

    @Autowired
    private CustomerAddressRepo customerAddressRepo;


    public CustomerInfoRes info(String email)
    {
        return CustomerInfoRes.from(findByEmail(email));
    }

    public CustomerDb findByEmail(String email)
    {
        return repository.findByEmailCustom(email);
    }

    public List<Long> getFavoriteList(String email)
    {
        return repository.getFavoriteList(email);
    }

    public Long register(CustomerDb customer)
    {
        return repository.save(customer).getId();
    }

    public void update(CustomerDb customer)
    {
        saveCustomer(customer);
    }

    @Deprecated
    public void delete(String email)
    {
        CustomerDb customer = findByEmail(email);
        customer.setActive(0);
        saveCustomer(customer);
    }

    private Long saveCustomer(CustomerDb customer)
    {
        return repository.save(customer).getId();
    }

    public List<AddressObject> getAllAddresses(Long customerId)
    {
        return customerAddressRepo.findAllByCustomerIdCustom(customerId)
                .stream()
                .map(element -> AddressObject.from(element))
                .collect(Collectors.toList());
    }

    public boolean saveAddress(Long customerId, AddressObject address)
    {
        if (address.getId() != null)
        {
            customerAddressRepo.save(CustomerAddressDb.updateFrom(customerId, address));
            return true;
        }
        customerAddressRepo.save(CustomerAddressDb.from(customerId, address));

        //TODO
        // need to set up the return types for save and update operations

        return true;
    }

    public void deleteAddress(Long addressId)
    {
        customerAddressRepo.deleteByAddressIdCustom(addressId);
    }
}
