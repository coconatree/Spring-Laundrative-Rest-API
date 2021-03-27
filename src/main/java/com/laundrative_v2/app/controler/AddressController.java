package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.Address.CountyDb;
import com.laundrative_v2.app.beans.db.Address.DistrictDb;
import com.laundrative_v2.app.beans.db.Address.NeighborhoodDb;
import com.laundrative_v2.app.beans.db.Address.ProvinceDb;
import com.laundrative_v2.app.dao.AddressDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController
{
    @Autowired
    AddressDao addressDao;

    @GetMapping(value = "/province")
    public ResponseEntity<Object> getAllP()
    {
        List<ProvinceDb> response = addressDao.getAllProvince();

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/district/{provinceId}")
    public ResponseEntity<Object> getAllD(@PathVariable(value = "provinceId") Long provinceId)
    {
        List<DistrictDb> response  = addressDao.getAllDistrict(provinceId);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/county/{districtId}")
    public ResponseEntity<Object> getAllC(@PathVariable(value = "districtId") Long districtId)
    {
        List<CountyDb> response =  addressDao.getAllCounty(districtId);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/neighborhood/{countyId}")
    public ResponseEntity<Object> getAllN(@PathVariable(value = "countyId") Long countyId)
    {
        List<NeighborhoodDb> response = addressDao.getAllNeighborhood(countyId);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
