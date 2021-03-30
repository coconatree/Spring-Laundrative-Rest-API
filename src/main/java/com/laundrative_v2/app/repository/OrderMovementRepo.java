package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.OrderMovementDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderMovementRepo extends CrudRepository<OrderMovementDb, Long>
{ }
