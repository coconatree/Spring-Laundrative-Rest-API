package com.laundrative_v2.app.repository.addressRepo;

import com.laundrative_v2.app.beans.db.addressDb.CountyDb;
import com.laundrative_v2.app.beans.db.addressDb.DistrictDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountyRepo extends JpaRepository<CountyDb, Long>
{
    List<CountyDb> findByDistrictId(Long districtId);

    @Query(value = "select p from CountyDb p where p.districtId = :districtId")
    List<CountyDb> findAllByDistrictId(@Param("districtId") Long districtId);
}
