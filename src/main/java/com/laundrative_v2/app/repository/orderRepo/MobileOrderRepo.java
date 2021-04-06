package com.laundrative_v2.app.repository.orderRepo;

import com.laundrative_v2.app.beans.db.orderDb.MobileOrderDb;
import com.laundrative_v2.app.beans.db.orderDb.OrderDetailsDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileOrderRepo extends CrudRepository<MobileOrderDb, Long>
{
    @Query(value = "delete o from mobile_siparis o where o.siparis_id = :orderId", nativeQuery = true)
    void deleteOrder(@Param("orderId") Long orderId);

    @Query(value = "select o.siparis_id from mobile_siparis o where o.siparis_id = :orderId", nativeQuery = true)
    Long findByIdCustom(@Param("orderId") Long orderId);

    @Query(value = "update o from mobile_siparis o set o.notlar = :notes where o.siparis_id = :orderId", nativeQuery = true)
    void updateCustom(
            @Param("orderId") Long orderId,
            @Param("notes") String notes
    );
}
