package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.CountyDb;
import com.laundrative_v2.app.beans.db.DistrictDb;
import com.laundrative_v2.app.beans.db.ProvinceDb;
import com.laundrative_v2.app.repository.CountyRepo;
import com.laundrative_v2.app.repository.DistrictRepo;
import com.laundrative_v2.app.repository.ProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDao
{
    @Autowired
    ProvinceRepo provinceRepo;

    @Autowired
    DistrictRepo districtRepo;

    @Autowired
    CountyRepo countyRepo;

    public List<ProvinceDb> getAllProvince()
    {
        return provinceRepo.findAll();
    }

    public List<DistrictDb> getAllDistrict(Long provinceId)
    {
        return districtRepo.findByProvinceId(provinceId);
    }

    public List<CountyDb> getAllCounty(Long districtId)
    {
        return countyRepo.findByDistrictId(districtId);
    }
}
