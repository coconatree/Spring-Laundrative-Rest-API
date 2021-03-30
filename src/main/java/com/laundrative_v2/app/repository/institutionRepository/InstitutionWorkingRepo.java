package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionWorkingDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;


public interface InstitutionWorkingRepo extends JpaRepository<InstitutionWorkingDb, Long>
{
    @Query(value = "select * from kurum k join kurum_calisma c on (c.kurum_id = k.id) where baslama_saati <= :saat and bitis_saati >= :saat and gun = :gun ", nativeQuery = true)
    List<InstitutionWorkingDb> findByTimeBetween(
            @Param("gun") int gun,
            @Param("saat") Time saat
    );

    /**
     *
     * @param list
     * @param day
     * @param time
     * @return
     */

    @Query(value = "SELECT k.institutionId FROM InstitutionWorkingDb k WHERE k.institutionId IN :list AND k.day = :day AND k.startingTime <= :time AND :time <= k.endingTime")
    List<Long> searchByDayTime(@Param("list") List<Long> list, @Param("day") Integer day, @Param("time") Time time);
}
