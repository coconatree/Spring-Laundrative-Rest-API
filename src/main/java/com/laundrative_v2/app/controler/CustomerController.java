package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.customer.AddressObject;
import com.laundrative_v2.app.beans.json.customer.request.CustomerPostReq;
import com.laundrative_v2.app.beans.json.customer.request.DeleteFormReq;
import com.laundrative_v2.app.beans.json.customer.response.CustomerInfoRes;
import com.laundrative_v2.app.dao.CustomerDao;
import com.laundrative_v2.app.service.CustomerService;
import com.laundrative_v2.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController
{
    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerService service;

    @PostMapping(value= "/na/customer/register")
    public ResponseEntity<Object> register(@RequestBody CustomerPostReq request)
    {
        Long id = customerDao.register(CustomerDb.from(request));

        if(id != null)
        {
            return Utility.createResponse("", "", HttpStatus.BAD_REQUEST);
        }
        else
            return Utility.createResponse("", id, HttpStatus.OK);
    }

    @GetMapping(value= "/customer")
    public ResponseEntity<Object> getCustomerInfo(HttpServletRequest request)
    {
        CustomerInfoRes response  = customerDao.info(request.getUserPrincipal().getName());

        if(response == null)
        {
            return Utility.createResponse("", "", HttpStatus.BAD_REQUEST);
        }
        else
            return Utility.createResponse("", response, HttpStatus.OK);
    }

    @PutMapping(value = "/customer")
    public ResponseEntity<Object> updateCustomer(@RequestBody CustomerPostReq requestBody)
    {
        service.updateCustomer(requestBody);
        return Utility.createResponse("", "", HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<Object> deleteCustomer(HttpServletRequest request, @RequestBody DeleteFormReq form)
    {
        System.out.println(form.getReason());
        System.out.println(form.getExplanation());

        // Form will be saved

        customerDao.delete(request.getUserPrincipal().getName());

        return Utility.createResponse("", "", HttpStatus.OK);
    }

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
