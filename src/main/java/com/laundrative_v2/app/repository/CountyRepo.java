package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.CountyDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountyRepo extends JpaRepository<CountyDb, Long>
{
    List<CountyDb> findByDistrictId(Long districtId);
}
