package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.db.customerDb.CustomerDeletedDb;
import com.laundrative_v2.app.repository.customerRepo.CustomerDeletedRepo;
import com.laundrative_v2.app.repository.customerRepo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerDao
{
    @Autowired
    private CustomerRepo repository;

    @Autowired
    private CustomerDeletedRepo deletedRepository;

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

    public void update(CustomerDb customer) { saveCustomer(customer); }

    public void delete(CustomerDb customer) { repository.delete(customer); }

    public void saveToDeleted(CustomerDeletedDb deleted) { deletedRepository.save(deleted); }

    private Long saveCustomer(CustomerDb customer)
    {
        return repository.save(customer).getId();
    }
}
