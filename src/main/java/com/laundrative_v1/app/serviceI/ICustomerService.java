package com.laundrative_v1.app.serviceI;

import com.laundrative_v1.app.dao.CustomerDao;

public interface ICustomerService
{
    CustomerDao read(Long id);
    CustomerDao update(CustomerDao obj);
    CustomerDao create(CustomerDao obj);
    CustomerDao delete(Long id);
}
