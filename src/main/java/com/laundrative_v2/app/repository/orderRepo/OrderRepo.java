package com.laundrative_v2.app.repository.orderRepo;

import com.laundrative_v2.app.beans.db.orderDb.OrderDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderRepo extends CrudRepository<OrderDb, Long>
{
    @Query(value = "select o.id from siparis o where o.id = :id", nativeQuery = true)
    Long findByIdCustom(@Param("id") Long id);

    @Query(value = "select * from siparis s where s.musteri_id = :id", nativeQuery = true)
    List<OrderDb> findAllCustom(@Param("id") Long id);


    @Query(value = "delete o from siparis o where o.id = :id", nativeQuery = true)
    void deleteOrder(@Param("id") Long id);

    @Query(value = "update o from siparis o where o.id = :id set o.tutar = :total set o.notlar = :notes set o.indirim = :discount set o.teslim_alma_tarihi = :receivingDate set o.teslim_alma_adres = :receivingAddress set o.teslim_etme_tarihi = :deliveryDate set o.teslim_etme_adres = :deliveryAddress", nativeQuery = true)
    void updateCustom(@Param("id") Long id,
                      @Param("total") BigDecimal total,
                      @Param("notes") String notes,
                      @Param("discount") BigDecimal discount,
                      @Param("receivingDate") Date receivingDate,
                      @Param("receivingAddress") String receivingAddress,
                      @Param("deliveryDate") Date deliveryDate,
                      @Param("deliveryAddress") String deliveryAddress
                      );

    boolean existsById(Long id);
}