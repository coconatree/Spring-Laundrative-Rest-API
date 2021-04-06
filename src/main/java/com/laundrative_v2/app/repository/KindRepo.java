package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.KindDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KindRepo extends JpaRepository<KindDb, Long>
{
    @Query(value = "select * from cins c " +
            " where c.id = :id and(c.kategori = :category) and (lower(c.adi) like :param);", nativeQuery = true)
    KindDb searchByIdAndCategoryAndParam(
            @Param(value = "id") Long id,
            @Param(value = "category") Long category,
            @Param(value = "param") String param
    );

    List<KindDb> findAllByName(String name);
    List<KindDb> findAllByInstitutionId(Long id);
}
