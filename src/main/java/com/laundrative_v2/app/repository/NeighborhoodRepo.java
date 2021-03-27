package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeighborhoodRepo extends JpaRepository<NeighborhoodDb, Long>
{
    List<NeighborhoodDb> findByCountyId(Long countyId);
}
