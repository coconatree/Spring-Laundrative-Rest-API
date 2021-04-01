package com.laundrative_v2.app.repository.institutionRepo;

import com.laundrative_v2.app.beans.db.institutionDb.InstitutionWorkingDb;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
