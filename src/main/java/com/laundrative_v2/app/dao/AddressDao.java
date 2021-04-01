package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.addressDb.CountyDb;
import com.laundrative_v2.app.beans.db.addressDb.DistrictDb;
import com.laundrative_v2.app.beans.db.addressDb.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.addressDb.ProvinceDb;
import com.laundrative_v2.app.repository.addressRepo.CountyRepo;
import com.laundrative_v2.app.repository.addressRepo.DistrictRepo;
import com.laundrative_v2.app.repository.addressRepo.NeighborhoodRepo;
import com.laundrative_v2.app.repository.addressRepo.ProvinceRepo;
import com.sun.org.slf4j.internal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDao
{
    private static Logger logger = LoggerFactory.getLogger(AddressDao.class);

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
