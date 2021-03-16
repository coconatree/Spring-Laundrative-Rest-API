package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.OrderMovementDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMovementRepo extends JpaRepository<OrderMovementDb, Long>
{ }
