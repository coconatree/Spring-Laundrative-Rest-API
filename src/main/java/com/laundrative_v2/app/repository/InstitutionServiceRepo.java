package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionServiceDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutionServiceRepo extends JpaRepository<InstitutionServiceDb, Long>
{
    List<InstitutionServiceDb> findByNeighborhoodId(Long id);
    InstitutionServiceDb findByInstitutionId(Long id);
}
