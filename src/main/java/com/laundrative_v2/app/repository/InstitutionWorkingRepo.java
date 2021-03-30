package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionWorkingDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface InstitutionWorkingRepo extends JpaRepository<InstitutionWorkingDb, Long>
{
    /**
    @Query(value = "SELECT k.institutionId FROM InstitutionWorkingDb k WHERE k.institutionId IN :list AND k.day = :day AND k.startingTime <= :time AND :time <= k.endingTime")
    List<Long> searchByDayTime(@Param("list") List<Long> list, @Param("day") Integer day, @Param("time") Time time);
     */

    @Query(value = "select k.id from kurum k join kurum_calisma c on (c.kurum_id = k.id) where k.id in ?list and baslama_saati <= ?time and bitis_saati >= ?time and gun = ?day ", nativeQuery = true)
    List<Long> searchByDayTime(
            @Param("list") List<Long> list,
            @Param("gun")  Integer day,
            @Param("saat") Time time
    );

}
