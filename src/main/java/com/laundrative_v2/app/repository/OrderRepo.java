package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.CustomerDb;
import com.laundrative_v2.app.beans.db.OrderDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderDb, Long>
{
    List<OrderDb> findByCustomer(CustomerDb obj);
}
