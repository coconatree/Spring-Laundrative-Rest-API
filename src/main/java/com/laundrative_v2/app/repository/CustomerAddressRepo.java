package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.CustomerAddressDb;
import com.laundrative_v2.app.beans.db.CustomerDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddressDb, Long>
{
    List<CustomerAddressDb> findByCustomerId(Long obj);
}
