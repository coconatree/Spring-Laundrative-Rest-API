package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.InstitutionDb;
import org.springframework.data.repository.CrudRepository;

public interface InstitutionRepo extends CrudRepository<InstitutionDb, Long> { }
