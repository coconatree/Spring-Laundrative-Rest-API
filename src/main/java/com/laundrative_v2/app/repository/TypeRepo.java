package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.TypeDb;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TypeRepo extends JpaRepository<TypeDb, Long> { }