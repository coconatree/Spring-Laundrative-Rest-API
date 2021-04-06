package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.customerDb.CustomerDb;
import com.laundrative_v2.app.beans.json.address.request.AddressAddReq;
import com.laundrative_v2.app.beans.json.address.response.AddressListRes;
import com.laundrative_v2.app.beans.json.customer.AddressObject;
import com.laundrative_v2.app.beans.json.customer.request.CustomerPostReq;
import com.laundrative_v2.app.beans.json.customer.request.CustomerDelReq;
import com.laundrative_v2.app.beans.json.customer.response.CustomerInfoRes;
import com.laundrative_v2.app.dao.CustomerDao;
import com.laundrative_v2.app.exception.EmailNotFoundException;
import com.laundrative_v2.app.service.CustomerService;
import com.laundrative_v2.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomerController
{
    @Autowired
    CustomerService service;

    @PostMapping(value= "/na/customer/register")
    public ResponseEntity<Object> register(@RequestBody CustomerPostReq request)
    {
        Long id = service.registerCustomer(request);

        if(id == null)
        {
            return Utility.createResponse("", "", HttpStatus.BAD_REQUEST);
        }
        else
            return Utility.createResponse("", id, HttpStatus.OK);
    }

    @GetMapping(value= "/customer")
    public ResponseEntity<Object> getCustomerInfo(HttpServletRequest request)
    {
        CustomerInfoRes response  = service.info(request.getUserPrincipal().getName());

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
        return Utility.createResponse("", "UPDATED", HttpStatus.OK);
    }

    @Autowired
    private CustomerDao customerDao;

    @DeleteMapping(value = "/")
    public ResponseEntity<Object> deleteCustomer(HttpServletRequest request, @RequestBody CustomerDelReq requestBody)
    {
        service.deleteCustomer(request.getUserPrincipal().getName(), requestBody);

        return Utility.createResponse("", "", HttpStatus.OK);
    }

    /** Controllers for the addresses */

     @GetMapping(value = "/address/all")
     public ResponseEntity<Object> getAll(HttpServletRequest request)
     {
         List<AddressObject> response =  service.getAllAddresses(getEmailFromRequestAndValidate(request));
         return Utility.createResponse("", response, HttpStatus.OK);
     }

     @PostMapping(value = "/address")
     public ResponseEntity<Object> saveAddress(HttpServletRequest request, @RequestBody AddressAddReq address)
     {
        Long id = service.saveAddress(getEmailFromRequestAndValidate(request), address);
        return Utility.createResponse("", id, HttpStatus.OK);
     }

    @PutMapping(value = "/address/{addressId}")
    public ResponseEntity<Object> updateAddress(
            HttpServletRequest request,
            @PathVariable(value = "addressId") Long addressId,
            @RequestBody AddressAddReq address
    )
    {
        service.updateAddress(getEmailFromRequestAndValidate(request), addressId, address);
        return Utility.createResponse("", "UPDATED", HttpStatus.OK);
    }

     @DeleteMapping(value = "/address/delete/{addressId}")
     public ResponseEntity<Object> deleteAddress(HttpServletRequest request, @PathVariable(value = "addressId") Long addressId)
     {
        service.deleteAddress(getEmailFromRequestAndValidate(request), addressId);
        return Utility.createResponse("", "DELETED", HttpStatus.OK);
     }

     private String getEmailFromRequestAndValidate(HttpServletRequest request)
     {
        if(request == null || request.getUserPrincipal().getName() == null)
        {
            throw new EmailNotFoundException("", HttpStatus.OK);
        }
        else{
            return request.getUserPrincipal().getName();
        }
     }

}
