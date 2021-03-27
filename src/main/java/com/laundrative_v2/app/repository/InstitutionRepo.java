package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepo extends JpaRepository<InstitutionDb, Long> { }
