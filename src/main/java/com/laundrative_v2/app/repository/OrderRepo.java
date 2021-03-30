package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.OrderDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository<OrderDb, Long>
{ }
