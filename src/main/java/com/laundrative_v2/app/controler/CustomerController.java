package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.json.customer.AddressObject;
import com.laundrative_v2.app.dao.CustomerDao;
import com.laundrative_v2.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    CustomerDao customerDao;

    /** Controllers for the addresses */

    @GetMapping(value = "/address/all/{customerId}")
    public ResponseEntity<Object> getAll(@PathVariable(value = "customerId") Long customerId)
    {
        return Utility.createResponse("", customerDao.getAllAddresses(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/address/{customerId}")
    public ResponseEntity<Object> saveAddress(@PathVariable(value = "customerId") Long customerId, @RequestBody AddressObject address)
    {
        return Utility.createResponse("", customerDao.saveAddress(customerId, address), HttpStatus.OK);
    }

    @DeleteMapping(value = "/address/delete/{addressId}")
    public ResponseEntity<Object> deleteAddress(@PathVariable(value = "addressId") Long addressId)
    {
        customerDao.deleteAddress(addressId);
        return Utility.createResponse("", "DELETED", HttpStatus.OK);
    }
}
