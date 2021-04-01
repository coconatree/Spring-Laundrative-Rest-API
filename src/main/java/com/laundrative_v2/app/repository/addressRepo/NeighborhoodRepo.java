package com.laundrative_v2.app.repository.addressRepo;

import com.laundrative_v2.app.beans.db.addressDb.NeighborhoodDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface NeighborhoodRepo extends CrudRepository<NeighborhoodDb, Long>
{
    List<NeighborhoodDb> findByCountyId(Long countyId);
    Optional<NeighborhoodDb> findById(Long id);
}
