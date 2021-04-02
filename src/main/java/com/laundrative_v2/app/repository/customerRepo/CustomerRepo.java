package com.laundrative_v2.app.repository.customerRepo;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<CustomerDb, Long> { }
