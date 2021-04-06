package com.laundrative_v2.app.repository.addressRepo;

import com.laundrative_v2.app.beans.db.addressDb.ProvinceDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvinceRepo extends JpaRepository<ProvinceDb, Long>
{
    @Query(value = "select * from adres_il il", nativeQuery = true)
    List<ProvinceDb> findAllProvince();

    @Query(value = "select semt.semt_adi, mahalle.id, mahalle.mahalle_adi from adres_il il\n" +
            "    join adres_ilce ilce on (il.id = ilce.il_id)\n" +
            "    join adres_semt semt on (ilce.id = semt.ilce_id)\n" +
            "    join adres_mahalle mahalle on (semt.id = mahalle.semt_id)\n" +
            "    where il.id = :provinceId", nativeQuery = true)
    List<List<String>> getAllFromProvinceId(@Param("provinceId") Long provinceId);
}
