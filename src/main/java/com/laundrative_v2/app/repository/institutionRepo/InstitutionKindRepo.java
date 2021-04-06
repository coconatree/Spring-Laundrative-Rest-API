package com.laundrative_v2.app.repository.institutionRepo;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionKindDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionKindRepo extends JpaRepository<InstitutionKindDb, Long>
{
    List<InstitutionKindDb> findAllByInstitutionId(Long id);
    Boolean existsByInstitutionIdAndKindCategoryIdAndKindId(Long instId, Long categoryId, Long kindId);
}
