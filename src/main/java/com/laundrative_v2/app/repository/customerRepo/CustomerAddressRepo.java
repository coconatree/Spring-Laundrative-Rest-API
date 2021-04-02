package com.laundrative_v2.app.repository.customerRepo;

import com.laundrative_v2.app.beans.db.customerDb.CustomerAddressDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddressDb, Long>
{
    @Query(value = "select * from musteri_adres a where a.musteri_id = :customerId", nativeQuery = true)
    List<CustomerAddressDb> findAllByCustomerIdCustom(@Param("customerId") Long customerId);

    @Query(value = "delete a from musteri_adres a where a.id = :addressId", nativeQuery = true)
    void deleteByAddressIdCustom(@Param("addressId") Long addressId);
}
