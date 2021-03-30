package com.laundrative_v2.app.repository;


//TODO
//-Repository accesses should be limited and should extends only from the necessary Repository
//FIXME
//-should disallow the unnecessary methods

import com.laundrative_v2.app.beans.db.Institution.InstitutionCategoryDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InstitutionCategoryRepo extends JpaRepository<InstitutionCategoryDb, Long>
{
    @Query(value = "SELECT db.institutionId FROM InstitutionCategoryDb db WHERE db.institutionId IN :list AND db.categoryId IN :categoryId")
    List<Long> searchByList(@Param("list") List<Long> list, @Param("categoryId") Long [] categoryId);

    Boolean existsByInstitutionIdAndCategoryId(Long institutionId, Long categoryId);
}