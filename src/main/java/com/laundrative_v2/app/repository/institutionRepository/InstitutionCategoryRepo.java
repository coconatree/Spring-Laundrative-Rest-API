package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionCategoryDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value = "SELECT db FROM InstitutionCategoryDb db WHERE db.institutionId = :id AND db.categoryId = :categoryId")
    List<InstitutionCategoryDb> findInstitutionIdByInstitutionIdAndCategoryId(@Param("id") Long id, @Param("categoryId") Long categoryId);

    Boolean existsByInstitutionIdAndAndCategoryId(Long id, Long categoryId);
}
