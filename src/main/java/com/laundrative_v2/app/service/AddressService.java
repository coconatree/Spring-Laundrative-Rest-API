package com.laundrative_v2.app.service;

import com.laundrative_v2.app.beans.db.addressDb.ProvinceDb;
import com.laundrative_v2.app.beans.db.customerDb.CustomerAddressDb;
import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.address.request.AddressAddReq;
import com.laundrative_v2.app.beans.json.address.response.AddressListRes;
import com.laundrative_v2.app.beans.json.address.response.AddressProvinceRes;
import com.laundrative_v2.app.beans.json.customer.AddressObject;
import com.laundrative_v2.app.beans.json.institution.NeighborhoodInfo;
import com.laundrative_v2.app.dao.AddressDao;
import com.laundrative_v2.app.dao.CustomerAddressDao;
import com.laundrative_v2.app.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService
{
    @Autowired
    private AddressDao addressDao;

    @Autowired
    private CustomerAddressDao customerAddressDao;

    public List<ProvinceDb> findAllProvinces()
    {
        return addressDao.getAllProvince();
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

    protected void deleteCustomerAddress(Long addressId)
    {
        customerAddressDao.deleteAddress(addressId);
    }

    protected List<AddressObject> getAllCustomerAddresses(CustomerDb customer)
    {
        return customerAddressDao.getAllAddresses(customer.getId())
                .stream()
                .map(element -> AddressObject.from(element))
                .collect(Collectors.toList());
    }

    protected Long saveCustomerAddress(Long customerId, AddressAddReq addressObject)
    {
        return customerAddressDao.saveAddress(customerId, addressObject);
    }

    protected Long updateCustomerAddress(Long addressId, AddressAddReq addressObject)
    {
        return customerAddressDao.updateAddress(addressId, addressObject);
    }

    protected Boolean owns(Long customerId, Long addressId)
    {
        return customerAddressDao.owns(customerId, addressId);
    }
}
