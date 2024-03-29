package com.laundrative_v2.app.repository.customerRepo;

import com.laundrative_v2.app.beans.db.customerDb.CustomerAddressDb;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepo extends JpaRepository<CustomerAddressDb, Long>
{
    @Query(value = "select * from musteri_adres a where a.musteri_id = :customerId and (aktif = 1)", nativeQuery = true)
    List<CustomerAddressDb> findAllByCustomerIdCustom(@Param("customerId") Long customerId);

    @Query(value = "delete a from musteri_adres a where a.id = :addressId", nativeQuery = true)
    void deleteByAddressIdCustom(@Param("addressId") Long addressId);

    Boolean existsByCustomerIdAndId(Long customerId, Long addressId);
}
