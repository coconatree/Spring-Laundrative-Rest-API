package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.Address.CountyDb;
import com.laundrative_v2.app.beans.db.Address.DistrictDb;
import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.Address.ProvinceDb;
import com.laundrative_v2.app.repository.CountyRepo;
import com.laundrative_v2.app.repository.DistrictRepo;
import com.laundrative_v2.app.repository.NeighborhoodRepo;
import com.laundrative_v2.app.repository.ProvinceRepo;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDao
{
    private static final Logger logger = LoggerFactory.getLogger(AddressDao.class);

    @Autowired
    ProvinceRepo provinceRepo;

    @Autowired
    DistrictRepo districtRepo;

    @Autowired
    CountyRepo countyRepo;

    @Autowired
    NeighborhoodRepo neighborhoodRepo;

    public List<ProvinceDb> getAllProvince()
    {
        try
        {
            return provinceRepo.findAll();
        }
        catch (Exception e)
        {
            logger.warn(String.valueOf("Error cause : \n " + e.getCause()));
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn(String.valueOf("Error stack trace : \n " + e.getStackTrace()));

            return null;
        }
    }

    public List<DistrictDb> getAllDistrict(Long provinceId)
    {
        try
        {
            return districtRepo.findByProvinceId(provinceId);
        }
        catch (Exception e)
        {
            logger.warn(String.valueOf("Error cause : \n " + e.getCause()));
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn(String.valueOf("Error stack trace : \n " + e.getStackTrace()));

            return null;
        }
    }

    public List<CountyDb> getAllCounty(Long districtId)
    {
        try
        {
            return countyRepo.findByDistrictId(districtId);
        }
        catch (Exception e)
        {
            logger.warn(String.valueOf("Error cause : \n " + e.getCause()));
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn(String.valueOf("Error stack trace : \n " + e.getStackTrace()));

            return null;
        }
    }

    public List<NeighborhoodDb> getAllNeighborhood(Long countyId)
    {
        try
        {
            return neighborhoodRepo.findByCountyId(countyId);
        }
        catch (Exception e)
        {
            logger.warn(String.valueOf("Error cause : \n " + e.getCause()));
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn(String.valueOf("Error stack trace : \n " + e.getStackTrace()));

            return null;
        }
    }
}
