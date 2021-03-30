package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionKindDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutionKindRepo extends JpaRepository<InstitutionKindDb, Long>
{
    List<InstitutionKindDb> findAllByInstitutionId(Long id);
}
