package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface InstitutionRepo extends JpaRepository<InstitutionDb, Long>
{
    //TODO
    // -Should ask about this once more

    Optional<InstitutionDb> findById(Long id);
}
