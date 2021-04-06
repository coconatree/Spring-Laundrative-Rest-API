package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.customerDb.CustomerAddressDb;
import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.address.request.AddressAddReq;
import com.laundrative_v2.app.beans.json.customer.AddressObject;
import com.laundrative_v2.app.repository.customerRepo.CustomerAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressDao
{
    @Autowired
    private CustomerAddressRepo customerAddressRepo;

    public List<CustomerAddressDb> getAllAddresses(Long customerId)
    {
        return customerAddressRepo.findAllByCustomerIdCustom(customerId);
    }

    public Long saveAddress(Long customerId, AddressAddReq address)
    {
        return customerAddressRepo.save(CustomerAddressDb.from(customerId, address)).getId();
    }

    public Long updateAddress(Long addressId, AddressAddReq address)
    {
        CustomerAddressDb original = getById(addressId);
        return customerAddressRepo.save(CustomerAddressDb.updateFrom(
                original, address)).getId();
    }

    public void deleteAddress(Long addressId)
    {
        CustomerAddressDb original = getById(addressId);
        original.delete();
        customerAddressRepo.save(original);
    }

    private CustomerAddressDb getById(Long addressId)
    {
        return customerAddressRepo.findById(addressId).get();
    }

    public boolean owns(Long customerId, Long addressId)
    {
        return customerAddressRepo.existsByCustomerIdAndId(customerId, addressId).booleanValue();
    }
}
