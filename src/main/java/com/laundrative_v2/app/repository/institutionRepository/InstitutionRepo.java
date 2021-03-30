package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface InstitutionRepo extends JpaRepository<InstitutionDb, Long>
{
    Optional<InstitutionDb> findById(Long id);
    List<InstitutionDb> findAllByIdIn(List<Long> idList);
}
