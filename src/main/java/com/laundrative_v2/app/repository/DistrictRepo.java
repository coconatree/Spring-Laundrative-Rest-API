package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Address.DistrictDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepo extends JpaRepository<DistrictDb, Long>
{
    List<DistrictDb> findByProvinceId(Long provinceId);
}
