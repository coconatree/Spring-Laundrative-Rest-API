package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.json.institution.request.InstDetSearchReq;
import com.laundrative_v2.app.beans.json.institution.request.InstProdSearchReq;
import com.laundrative_v2.app.beans.json.institution.request.InstSearchReq;
import com.laundrative_v2.app.beans.json.institution.response.InstDetailedRes;
import com.laundrative_v2.app.beans.json.institution.response.InstListQueryRes;
import com.laundrative_v2.app.beans.json.institution.WorkingHoursRes;
import com.laundrative_v2.app.beans.json.product.response.ProductRes;
import com.laundrative_v2.app.dao.InstitutionDao;
import com.laundrative_v2.app.service.InstitutionService;
import com.laundrative_v2.app.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/na/institution")
public class InstitutionController
{
    @Autowired
    InstitutionDao institutionDao;

    @Autowired
    InstitutionService service;

    @PostMapping("/list")
    public ResponseEntity<Object> getByRequestBodyObject(HttpServletRequest request, @RequestBody InstSearchReq requestBody)
    {
        List<InstListQueryRes> response;

        if(request != null && request.getUserPrincipal() != null)
            response = service.searchInstitution(requestBody, request.getUserPrincipal().getName());
        else
            response = service.searchInstitution(requestBody, null);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/searchP")
    public ResponseEntity<Object> searchProduct(@RequestBody InstProdSearchReq requestBody)
    {
        List<ProductRes> response = service.searchInstitutionProduct(requestBody);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/detailed")
    public ResponseEntity<Object> detailedSearch(@RequestBody InstDetSearchReq request)
    {
        List<InstDetailedRes> list = service.searchInstitutionsDetailed(request);

        if(list == null)
            return Utility.createResponse("", "", HttpStatus.BAD_REQUEST);
        else
            return Utility.createResponse("",list, HttpStatus.OK);
    }

    @GetMapping("/hours/{id}")
    public ResponseEntity<Object> getWorkingHours(@PathVariable(value = "id") Long id)
    {
        List<WorkingHoursRes> response = institutionDao.getWorkingHours(id);

        if(response == null)
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        else
            return Utility.createResponse("", response, HttpStatus.OK);
    }
}
