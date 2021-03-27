package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Address.ProvinceDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepo extends JpaRepository<ProvinceDb, Long> {}
