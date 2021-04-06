package com.laundrative_v2.app.repository.orderRepo;

import com.laundrative_v2.app.beans.db.orderDb.OrderDetailsDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderDetailsRepo extends CrudRepository<OrderDetailsDb, Long>
{
    @Query(value = "select o.siparis_id from siparis_detay o where o.siparis_id = :orderId", nativeQuery = true)
    List<Long> findByIdCustom(@Param("orderId") Long orderId);

    @Query(value = "select o from siparis_detay o where o.siparis_id = :orderId", nativeQuery = true)
    List<OrderDetailsDb> findByIdCustomObject(@Param("orderId") Long orderId);

    @Query(value = "select * from siparis_detay o where o.siparis_id = :orderId", nativeQuery = true)
    List<OrderDetailsDb> findAllByOrderId(@Param("orderId") Long orderId);
}
