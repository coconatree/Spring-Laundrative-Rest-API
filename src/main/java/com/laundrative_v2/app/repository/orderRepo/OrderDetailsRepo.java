package com.laundrative_v2.app.repository.orderRepo;

import com.laundrative_v2.app.beans.db.orderDb.OrderDetailsDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailsRepo extends CrudRepository<OrderDetailsDb, Long>
{
    @Query(value = "delete o from siparis_detay o where o.siparis_id = :orderId", nativeQuery = true)
    void deleteOrder(@Param("orderId") Long orderId);

    @Query(value = "select o.siparis_id from siparis_detay o where o.siparis_id = :orderId", nativeQuery = true)
    List<Long> findByIdCustom(@Param("orderId") Long orderId);

    @Query(value = "select o from siparis_detay o where o.siparis_id = :orderId", nativeQuery = true)
    List<OrderDetailsDb> findByIdCustomObject(@Param("orderId") Long orderId);

    @Query(value = "update o from siparis_detay o set o.kategori = :category set o.cins = :kind set o.tip = :type set o.adet = :amount set o.fiyat = :price where o.id = :id", nativeQuery = true)
    void updateCustom(@Param("id") Long id,
                      @Param("category") Long category,
                      @Param("kind") Long kind,
                      @Param("type") Long type,
                      @Param("amount") Integer amount,
                      @Param("price")BigDecimal price
                      );
}
