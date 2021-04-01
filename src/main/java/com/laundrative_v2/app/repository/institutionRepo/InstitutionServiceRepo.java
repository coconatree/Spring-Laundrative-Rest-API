package com.laundrative_v2.app.repository.institutionRepo;


import com.laundrative_v2.app.beans.db.institutionDb.InstitutionServiceDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstitutionServiceRepo extends JpaRepository<InstitutionServiceDb, Long>
{
    @Query(value = "SELECT service.institutionId FROM InstitutionServiceDb service WHERE service.neighborhoodId = :id")
    List<Long> searchBy(@Param("id") Long id);
}
