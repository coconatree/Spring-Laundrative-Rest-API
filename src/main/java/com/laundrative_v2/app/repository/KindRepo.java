package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.KindDb;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KindRepo extends CrudRepository<KindDb, Long>
{
    List<KindDb> findAllById(Long id);
    List<KindDb> findAllByCategoryAndId(Long category, Long id);
    List<KindDb> findAllByName(String name);
    List<KindDb> findAllByInstitutionId(Long id);
}
