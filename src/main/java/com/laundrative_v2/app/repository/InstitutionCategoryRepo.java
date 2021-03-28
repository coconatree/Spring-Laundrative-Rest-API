package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionCategoryDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutionCategoryRepo extends JpaRepository<InstitutionCategoryDb, Long>
{
    List<InstitutionCategoryDb> findByInstitutionId(Long id);
}
