package com.laundrative_v1.app.repository;

import com.laundrative_v1.app.dao.StoreDao;
import com.laundrative_v1.app.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StoreRepo extends JpaRepository<StoreEntity, Long> { }
