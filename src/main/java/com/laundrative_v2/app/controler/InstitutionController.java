package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.Institution.InstitutionDb;
import com.laundrative_v2.app.beans.db.Institution.InstitutionServiceDb;
import com.laundrative_v2.app.beans.json.InstitutionRequestJson;
import com.laundrative_v2.app.beans.json.InstitutionResponseJson;
import com.laundrative_v2.app.dao.InstitutionDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institution") // Url with /user/** will be set as USER role in spring security
public class InstitutionController
{
    @Autowired
    InstitutionDao institutionDao;

    @GetMapping("/")
    public ResponseEntity<Object> getByRequestBodyObject(@RequestBody InstitutionRequestJson object)
    {
        System.out.println("HEYOOOO");
        System.out.println("Object :  " + object.toString());

        List<InstitutionResponseJson> response =  institutionDao.readByObject(object);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{institutionId}")
    public ResponseEntity<Object> getById(@PathVariable(value = "institutionId") Long institutionId)
    {
        InstitutionDb response = institutionDao.read(institutionId);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
