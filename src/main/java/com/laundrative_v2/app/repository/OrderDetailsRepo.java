package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.OrderDetailsDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetailsDb, Long> { }
