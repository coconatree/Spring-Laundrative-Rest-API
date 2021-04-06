package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.addressDb.DistrictDb;
import com.laundrative_v2.app.beans.db.addressDb.ProvinceDb;
import com.laundrative_v2.app.beans.json.address.response.AddressListRes;
import com.laundrative_v2.app.beans.json.address.response.AddressProvinceRes;
import com.laundrative_v2.app.dao.AddressDao;
import com.laundrative_v2.app.service.AddressService;
import com.laundrative_v2.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/na/address")
public class AddressController
{
    @Autowired
    AddressDao addressDao;

    @Autowired
    private AddressService service;

    @GetMapping(value = "/province")
    public ResponseEntity<Object> getAllProvince()
    {
        List<AddressProvinceRes> response = service.findAllProvinces();

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/{provinceId}")
    public ResponseEntity<Object> findAllByProvinceId(@PathVariable("provinceId") Long provinceId)
    {
        List<AddressListRes> list = service.findAllByProvinceId(provinceId);

        if(list != null)
            return Utility.createResponse("", list, HttpStatus.OK);
        else
            return Utility.createResponse("", "", HttpStatus.BAD_REQUEST);
    }
}
