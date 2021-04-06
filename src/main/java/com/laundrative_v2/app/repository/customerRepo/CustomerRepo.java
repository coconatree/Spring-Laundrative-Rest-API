package com.laundrative_v2.app.repository.customerRepo;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<CustomerDb, Long>
{
    @Query(value = "select * from musteri where musteri.email = :email and musteri.aktif = 1", nativeQuery = true)
    CustomerDb findByEmailCustom(@Param("email") String email);

    @Query(value = "select mfk.kurum_id from musteri m" +
            "    join musteri_favori_kurum mfk on m.id = mfk.musteri_id" +
            "    where m.email = :email", nativeQuery = true)
    List<Long> getFavoriteList(@Param("email") String email);
}
