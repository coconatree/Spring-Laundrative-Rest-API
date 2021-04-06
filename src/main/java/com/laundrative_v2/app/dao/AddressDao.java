package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.addressDb.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.addressDb.ProvinceDb;
import com.laundrative_v2.app.repository.addressRepo.NeighborhoodRepo;
import com.laundrative_v2.app.repository.addressRepo.ProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDao
{
    @Autowired
    private ProvinceRepo repositoryP;

    @Autowired
    private NeighborhoodRepo repositoryN;

    public List<ProvinceDb> getAllProvince()
    {
        return repositoryP.findAllProvince();
    }

    public List<List<String>> getAllNeighborhoodFromProvinceId(Long provinceId)
    {
        return repositoryP.getAllFromProvinceId(provinceId);
    }

    public NeighborhoodDb getNeighborhoodById(Long id)
    {
        return repositoryN.findById(id).get();
    }
}
