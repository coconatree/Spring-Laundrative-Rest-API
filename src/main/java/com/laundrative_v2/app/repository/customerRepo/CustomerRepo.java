package com.laundrative_v2.app.repository.customerRepo;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends JpaRepository<CustomerDb, Long>
{
    @Query(value = "select * from musteri where musteri.email = :email", nativeQuery = true)
    CustomerDb findByEmailCustom(@Param("email") String email);
}
