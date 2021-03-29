package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.Institution.InstitutionServiceDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface InstitutionServiceRepo extends JpaRepository<InstitutionServiceDb, Long>
{
    List<InstitutionServiceDb> findAllByNeighborhoodDbId(Long id);
    InstitutionServiceDb findAllByNeighborhoodDbIdAndAndInstitutionId(Long neighborhoodId, Long institutionId);
}
