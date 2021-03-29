package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionCategoryDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//TODO
//-Repository accesses should be limited and should extends only from the necessary Repository
//FIXME
//-should disallow the unnecessary methods

public interface InstitutionCategoryRepo extends JpaRepository<InstitutionCategoryDb, Long>
{
    List<InstitutionCategoryDb> findByCategoryIdInAndInstitutionId(List<Long> categoryList, Long id);
    InstitutionCategoryDb findByInstitutionIdAndAndCategoryId(Long id, Long categoryId);
}
