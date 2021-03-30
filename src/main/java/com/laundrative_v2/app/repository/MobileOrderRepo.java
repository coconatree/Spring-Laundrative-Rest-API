package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.MobileOrderDb;
import org.springframework.data.repository.CrudRepository;


public interface MobileOrderRepo extends CrudRepository<MobileOrderDb, Long>
{ }
