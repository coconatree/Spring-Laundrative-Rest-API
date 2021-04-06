package com.laundrative_v2.app.repository.institutionRepo;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionCategoryDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionCategoryRepo extends CrudRepository<InstitutionCategoryDb, Long>
{
    List<InstitutionCategoryDb> findAllByInstitutionIdInAndCategoryIdIn(List<Long> idList, Long[] categories);
    List<InstitutionCategoryDb> findAllByInstitutionIdInAndCategoryId(List<Long> idList, Long category);

    Boolean existsByInstitutionIdAndCategoryId(Long institutionId, Long categoryId);
}
