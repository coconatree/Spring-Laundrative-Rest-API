package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionWorkingDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionWorkingRepo extends JpaRepository<InstitutionWorkingDb, Long> {
}
