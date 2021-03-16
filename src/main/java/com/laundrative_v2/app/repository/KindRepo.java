package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.KindDb;
import org.springframework.data.repository.CrudRepository;

public interface KindRepo extends CrudRepository<KindDb, Long>
{
    KindDb findByIdAndCategory(Long id, Long category);
}
