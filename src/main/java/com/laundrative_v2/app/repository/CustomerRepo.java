package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<CustomerDb, Long> { }
