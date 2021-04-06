package com.laundrative_v2.app.service;

import com.laundrative_v2.app.beans.json.address.response.AddressListRes;
import com.laundrative_v2.app.beans.json.address.response.AddressProvinceRes;
import com.laundrative_v2.app.beans.json.institution.NeighborhoodInfo;
import com.laundrative_v2.app.dao.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService
{
    @Autowired
    private AddressDao addressDao;

    public List<AddressProvinceRes> findAllProvinces()
    {
        return addressDao.getAllProvince()
                .stream()
                .map(e -> AddressProvinceRes.from(e))
                .collect(Collectors.toList());
    }

    public List<AddressListRes> findAllByProvinceId(Long provinceId)
    {
        return addressDao.getAllNeighborhoodFromProvinceId(provinceId)
                .stream()
                .map(element -> AddressListRes.from(
                        element.get(0),
                        Long.valueOf(element.get(1)),
                        element.get(2))).collect(Collectors.toList()
                );
    }

    protected NeighborhoodInfo getNeighborhoodInfoById(Long id)
    {
        return NeighborhoodInfo.from(addressDao.getNeighborhoodById(id));
    }
}
