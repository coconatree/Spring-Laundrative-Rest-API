package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.CustomerAddressDb;
import com.laundrative_v2.app.beans.json.CustomerAddressJson;
import com.laundrative_v2.app.repository.CustomerAddressRepo;
import com.laundrative_v2.app.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressDao
{
    @Autowired
    CustomerAddressRepo customerAddressRepo;

    @Autowired
    CustomerRepo customerRepo;

    public List<CustomerAddressDb> get(Long id)
    {
        return customerAddressRepo.findByCustomerId(id);
    }

    public CustomerAddressDb set(CustomerAddressJson obj)
    {
        // Validation needs to be done !!!

        CustomerAddressDb customerAddressDb = new CustomerAddressDb();

        if(customerRepo.existsById(obj.getCustomerId()))
        {
            customerAddressDb.setCustomer(customerRepo.findById(obj.getCustomerId()).get());
            customerAddressDb.setAddress(obj.getAddress());
            customerAddressDb.setReceiving(obj.getReceiving());
            customerAddressDb.setHandingOver(obj.getHandingOver());

            return customerAddressRepo.save(customerAddressDb);
        }
        return null;
    }

    public CustomerAddressDb update(CustomerAddressJson obj, Long addressId)
    {
        // Validation needs to be done !!!

        if(customerRepo.existsById(obj.getCustomerId()))
        {
            CustomerAddressDb customerAddressDb = null;

            for (CustomerAddressDb curr : customerAddressRepo.findByCustomerId(obj.getCustomerId()))
            {
                System.out.println(curr.getId() + " - " + addressId);

                if(curr.getId().equals(addressId))
                {
                    customerAddressDb = curr;
                    break;
                }
            }

            if(customerAddressDb != null)
            {
                customerAddressDb.setCustomer(customerRepo.findById(obj.getCustomerId()).get());
                customerAddressDb.setAddress(obj.getAddress());
                customerAddressDb.setReceiving(obj.getReceiving());
                customerAddressDb.setHandingOver(obj.getHandingOver());

                return customerAddressRepo.save(customerAddressDb);
            }
            return null;
        }
        return null;
    }

    public CustomerAddressDb delete(Long customerId, Long addressId)
    {
        if(customerRepo.existsById(customerId))
        {
            CustomerAddressDb customerAddressDb = null;

            for (CustomerAddressDb curr : customerAddressRepo.findByCustomerId(customerId))
            {
                if(curr.getId().equals(addressId))
                {
                    customerAddressRepo.delete(curr);
                    return curr;
                }
            }
        }
        return null;
    }
}
