package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.dao.AddressDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController
{
    @Autowired
    AddressDao addressDao;

    @GetMapping(value = "/province")
    public ResponseEntity<Object> getAllP()
    {
        return Utility.createResponse("", addressDao.getAllProvince(), HttpStatus.OK);
    }

    @GetMapping(value = "/district/{provinceId}")
    public ResponseEntity<Object> getAllD(@PathVariable(value = "provinceId") Long provinceId)
    {
        return Utility.createResponse("", addressDao.getAllDistrict(provinceId), HttpStatus.OK);
    }

    @GetMapping(value = "/county/{districtId}")
    public ResponseEntity<Object> getAllC(@PathVariable(value = "districtId") Long districtId)
    {
        return Utility.createResponse("", addressDao.getAllCounty(districtId), HttpStatus.OK);
    }
}
