package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.CustomerDb;
import com.laundrative_v2.app.beans.json.CustomerJson;
import com.laundrative_v2.app.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CustomerDao
{
    @Autowired
    private CustomerRepo repo;

    public CustomerDb read(Long id, String password)
    {
        if(repo.existsById(id))
        {
            return repo.findById(id).get();
        }
        return null;
    }

    public CustomerDb save(CustomerJson customer)
    {
        CustomerDb obj = new CustomerDb();

        obj.setName(customer.getName());
        obj.setPassword(customer.getPassword());
        obj.setTelephone(customer.getTelephone());
        obj.setEmail(customer.getEmail());
        obj.setCreationDate(new Date());
        obj.setUpdateDate(new Date());
        obj.setDiscountType(0);
        obj.setDiscountPercentage(0);

        CustomerDb ret = repo.save(obj);

        if(ret == null)
        {
            return null;
        }
        return ret;
    }

    public CustomerDb update(CustomerJson customer, Long id)
    {
        if(repo.existsById(id))
        {
            CustomerDb old = repo.findById(id).get();

            old.setName(customer.getName());
            old.setPassword(customer.getPassword());
            old.setTelephone(customer.getTelephone());
            old.setEmail(customer.getEmail());
            old.setUpdateDate(new Date());
            old.setDiscountType(0);
            old.setDiscountPercentage(0);

            repo.save(old);

            return old;
        }
        return null;
    }

    public CustomerDb delete(Long id, String password)
    {
        if(repo.existsById(id))
        {
            CustomerDb obj = repo.findById(id).get();

            if(obj.getPassword().equals(password))
            {
                repo.delete(obj);
                return obj;
            }
        }
        return null;
    }
}
