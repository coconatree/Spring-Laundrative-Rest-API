package com.laundrative_v2.app.repository.institutionRepository;

import com.laundrative_v2.app.beans.db.Institution.InstitutionKindDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionKindRepo extends JpaRepository<InstitutionKindDb, Long> { }
