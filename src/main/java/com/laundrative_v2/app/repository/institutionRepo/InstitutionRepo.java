package com.laundrative_v2.app.repository.institutionRepo;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.*;

@Repository
public interface InstitutionRepo extends JpaRepository<InstitutionDb, Long>
{
    Optional<InstitutionDb> findById(Long id);
    List<InstitutionDb> findAllByIdIn(List<Long> idList);

    @Query(value = "select k.id from kurum k" +
            "    join kurum_hizmet kh on (k.id = k.id = kh.kurum_id)" +
            "    join kurum_calisma kc on (k.id = kc.kurum_id)" +
            "    join kurum_kategori kk on (k.id = kk.kurum_id)" +
            "where kh.mahalle_id = :neighborhoodId and (kc.baslama_saati <= :time) and ( :time <= kc.bitis_saati);", nativeQuery = true)
    List<Long> getAllInstitutionFromNeighborhoodIdAndDate(@Param("neighborhoodId") Long neighborhoodId, @Param("time") Time time);



    @Query(value = "select kc.cins_id, kc.cins_kategori from kurum k" +
            " join kurum_hizmet kh on (k.id = kh.kurum_id)" +
            " join kurum_cins kc on (k.id = kc.kurum_id)" +
            " where kh.mahalle_id = :neighborhoodId", nativeQuery = true)
    List<List<Long>> getProductListAsKindCategoryByNeighborhoodId(@Param("neighborhoodId") Long neighborhoodId);

    @Query(value = "select k from kurum k" +
            " join kurum_cins c on (k.id = c.kurum_id)" +
            " where k.id in (:idNList) and (c.cins_kategori = :categoryId) and (c.cins_id = :kindId)", nativeQuery = true)
    List<InstitutionDb> detailedSearch(@Param("idNList") List<Long> idNList,
                                      @Param("categoryId") Integer categoryId,
                                      @Param("kindId") Long kindId
    );

    @Query(value = "select k.id from kurum k" +
            " join kurum_calisma kc on k.id = kc.kurum_id" +
            " where k.id in :instIdList" +
            " and kc.gun = :day", nativeQuery = true)
    List<Long> isOpenAndWorking(@Param("instIdList") List<Long> instIdList,
                                @Param("day") Integer day
    );
    @Query(value = "select k.id from kurum k join kurum_hizmet kh on k.id = kh.kurum_id where kh.mahalle_id = :idN", nativeQuery = true)
    List<Long> findAllByNeighborhoodId(@Param("idN") Long idN);
}

