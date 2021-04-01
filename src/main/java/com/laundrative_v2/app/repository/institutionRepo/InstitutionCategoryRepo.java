package com.laundrative_v2.app.repository.institutionRepo;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionCategoryDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstitutionCategoryRepo extends CrudRepository<InstitutionCategoryDb, Long>
{
    List<InstitutionCategoryDb> findAllByInstitutionIdInAndCategoryIdIn(List<Long> idList, Long[] categories);
    List<InstitutionCategoryDb> findAllByInstitutionIdInAndCategoryId(List<Long> idList, Long category);

    Boolean existsByInstitutionIdAndCategoryId(Long institutionId, Long categoryId);
}
