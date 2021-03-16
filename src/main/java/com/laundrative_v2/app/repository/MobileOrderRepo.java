package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.MobileOrderDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileOrderRepo extends JpaRepository<MobileOrderDb, Long>
{ }
