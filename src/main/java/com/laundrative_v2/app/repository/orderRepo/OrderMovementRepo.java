package com.laundrative_v2.app.repository.orderRepo;

import com.laundrative_v2.app.beans.db.orderDb.OrderMovementDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface OrderMovementRepo extends CrudRepository<OrderMovementDb, Long>
{
    @Query(value = "delete o from siparis_hareket o where o.siparis_id = :orderId", nativeQuery = true)
    void deleteOrder(@Param("orderId") Long orderId);

    @Query(value = "select o.siparis_id from siparis_haraket o where o.siparis_id = :orderId", nativeQuery = true)
    Long findByIdCustom(@Param("orderId") Long orderId);

    @Query(value = "update o from siparis_hareket o set o.tarih = :date set o.hareket_tipi = :movementType where o.siparis_id = :orderId", nativeQuery = true)
    void updateCustom(@Param("orderId") Long orderId,
                      @Param("date") Date date,
                      @Param("movementType") int movementType
    );

}
