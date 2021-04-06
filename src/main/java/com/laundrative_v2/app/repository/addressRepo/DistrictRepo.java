package com.laundrative_v2.app.repository.addressRepo;

import com.laundrative_v2.app.beans.db.addressDb.DistrictDb;
import com.laundrative_v2.app.beans.db.addressDb.ProvinceDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepo extends JpaRepository<DistrictDb, Long>
{
    @Query(value = "select db from DistrictDb db where db.provinceId = :provinceId")
    List<DistrictDb> findAllByProvinceId(@Param("provinceId") Long provinceId);
}
