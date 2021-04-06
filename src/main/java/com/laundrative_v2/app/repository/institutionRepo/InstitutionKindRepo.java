package com.laundrative_v2.app.repository.institutionRepo;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionDb;
import com.laundrative_v2.app.beans.db.institutionDb.InstitutionKindDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstitutionKindRepo extends JpaRepository<InstitutionKindDb, Long>
{
    //SELECT db FROM InstitutionKindDb db WHERE db.institutionId IN :list AND (db.kindCategoryId = :categoryId) AND (db.kindId = :kindId)
    @Query(value =  "select kc.kurum_id " +
                    "from kurum_cins kc " +
                    "where kc.cins_id = :kindId and (kc.kurum_id in :list) and kc.cins_kategori = :categoryId", nativeQuery = true)
    List<Long> detailedSearch(@Param("list") List<Long> list, @Param("categoryId") Long categoryId , @Param("kindId") Long kindId);
    List<InstitutionKindDb> findAllByInstitutionId(Long id);

    Boolean existsByInstitutionIdAndKindCategoryIdAndKindId(Long instId, Long categoryId, Long kindId);

    // List<InstitutionKindDb> findAllByInstitutionIdInAndAndKindCategoryIdAndKindId(List<Long> list, Long categoryId, Long kindId);

    //
}
