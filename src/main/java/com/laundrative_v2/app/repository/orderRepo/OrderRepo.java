package com.laundrative_v2.app.repository.orderRepo;

import com.laundrative_v2.app.beans.db.orderDb.OrderDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<OrderDb, Long>
{
    @Query(value = "select db from OrderDb db where db.customerId = :customerId and db.active = :active", nativeQuery = false)
    List<OrderDb> findAllByCustomerId(@Param("customerId") Long customerId, @Param("active") Integer active);

    boolean existsById(Long id);
}