package com.laundrative_v2.app.repository.institutionRepo;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionWorkingDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface InstitutionWorkingRepo extends JpaRepository<InstitutionWorkingDb, Long>
{
    List<InstitutionWorkingDb> findAllByInstitutionIdInAndDayAndStartingTimeLessThanEqualAndEndingTimeGreaterThanEqual
            (
                List<Long> list,
                Integer day,
                Time startingTime,
                Time endingTime
                );

    @Query(value = "select * from kurum_calisma k where k.kurum_id = :id", nativeQuery = true)
    List<InstitutionWorkingDb> findAllByIdCustom(@Param("id") Long id);
}
