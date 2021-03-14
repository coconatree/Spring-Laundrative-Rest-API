package com.laundrative_v1.app.repository;

import com.laundrative_v1.app.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoreRepo extends JpaRepository<StoreEntity, Long>
{ }
