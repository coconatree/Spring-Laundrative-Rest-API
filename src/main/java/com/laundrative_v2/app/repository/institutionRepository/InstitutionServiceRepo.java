package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.Institution.InstitutionServiceDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface InstitutionServiceRepo extends JpaRepository<InstitutionServiceDb, Long>
{
    List<InstitutionServiceDb> findAllByNeighborhoodDbId(Long id);
    InstitutionServiceDb findAllByNeighborhoodDbIdAndAndInstitutionId(Long neighborhoodId, Long institutionId);

//    @Query(value = "select * from kurum k join kurum_calisma c on (c.kurum_id = k.id) where baslama_saati <= :saat and bitis_saati >= :saat and gun = :gun ", nativeQuery = true)


    @Query(value = "SELECT k.institutionId FROM InstitutionServiceDb k WHERE k.neighborhoodDb.id = :neighborhoodId")
    List<Long> searchBy(@Param("neighborhoodId") Long neighborhoodId);
}
