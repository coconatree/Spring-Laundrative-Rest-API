package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.CustomerAddressJson;
import com.laundrative_v2.app.beans.json.CustomerJson;
import com.laundrative_v2.app.dao.CustomerAddressDao;
import com.laundrative_v2.app.dao.CustomerDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerAddressDao customerAddressDao;

    @GetMapping(value = "/{id}/{password}")
    public ResponseEntity<Object> get(@PathVariable(value =  "id") Long id, @PathVariable(value = "password") String password)
    {
        CustomerDb customerDb = customerDao.read(id, password);

        if(customerDb == null)
        {
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        }
        return Utility.createResponse("", customerDb, HttpStatus.OK);
    }

    @PostMapping(value = "/register/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> set(@RequestBody CustomerJson customer)
    {
        CustomerDb customerDb = customerDao.save(customer);

        if(customerDb == null)
        {
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        }
        return Utility.createResponse("", customerDb, HttpStatus.OK);
    }

    @PutMapping(value = "/user/update/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> put(@PathVariable(value = "id") Long id, @RequestBody CustomerJson customer)
    {
        CustomerDb customerDb = customerDao.update(customer, id);

        if(customerDb == null)
        {
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        }
        return Utility.createResponse("", customerDb, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/delete/{id}/{password}/")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, @PathVariable(value = "password") String password)
    {
        CustomerDb customerDb = customerDao.delete(id, password);

        if(customerDb == null)
        {
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        }
        return Utility.createResponse("", customerDb, HttpStatus.OK);
    }

    /** Controllers for the addresses */

    @GetMapping(value = "/user/address/{customerId}")
    public ResponseEntity<Object> getAddresses(@PathVariable(value = "customerId") Long customerId)
    {
        return Utility.createResponse("", customerAddressDao.get(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/user/address/")
    public ResponseEntity<Object> setAddress(@RequestBody CustomerAddressJson obj)
    {
        return Utility.createResponse("", customerAddressDao.set(obj), HttpStatus.OK);
    }

    @PutMapping(value = "/user/address/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> putAddress(@RequestBody CustomerAddressJson obj, @PathVariable(value = "addressId") Long addressId)
    {
        return Utility.createResponse("", customerAddressDao.update(obj, addressId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/address/{customerId}/{addressId}")
    public ResponseEntity<Object> deleteAddress(@PathVariable(name = "customerId") Long customerId, @PathVariable(value = "addressId") Long addressId)
    {
        return Utility.createResponse("", customerAddressDao.delete(customerId, addressId), HttpStatus.OK);
    }
}
