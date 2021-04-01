package com.laundrative_v2.app.repository.addressRepo;

import com.laundrative_v2.app.beans.db.addressDb.ProvinceDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepo extends JpaRepository<ProvinceDb, Long> {}
