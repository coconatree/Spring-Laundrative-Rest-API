package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionDb;
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
    List<InstitutionWorkingDb> findAllByInstitutionIdInAndDayAndStartingTimeLessThanEqualAndEndingTimeGreaterThanEqual
            (
                List<Long> list,
                Integer day,
                Time startingTime,
                Time endingTime
                );
}
