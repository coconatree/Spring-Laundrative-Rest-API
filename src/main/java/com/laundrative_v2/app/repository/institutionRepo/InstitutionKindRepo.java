package com.laundrative_v2.app.repository.institutionRepo;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionKindDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstitutionKindRepo extends JpaRepository<InstitutionKindDb, Long>
{
    List<InstitutionKindDb> findAllByInstitutionId(Long id);
    Boolean existsByInstitutionIdAndKindIdAndKindCategoryId(Long instId, Long kindId, Long categoryId);
}
