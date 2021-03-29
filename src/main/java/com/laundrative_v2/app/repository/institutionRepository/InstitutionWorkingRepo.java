package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionWorkingDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InstitutionWorkingRepo extends JpaRepository<InstitutionWorkingDb, Long>
{
    InstitutionWorkingDb findByInstitutionIdAndStartingTimeIsBeforeAndEndingTimeIsAfterAndAndDay(Long id, Time time1, Time time2, Integer day);
    InstitutionWorkingDb findByInstitutionIdAndDay(Long id, Integer day);
    List<InstitutionWorkingDb> findAllByInstitutionId(Long id);
}
