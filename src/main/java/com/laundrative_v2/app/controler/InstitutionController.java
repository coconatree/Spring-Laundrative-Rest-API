package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.json.Response.InstitutionInfoQueryRes;
import com.laundrative_v2.app.beans.json.Response.InstListQueryRes;
import com.laundrative_v2.app.dao.InstitutionDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/institution")
public class InstitutionController
{
    @Autowired
    InstitutionDao institutionDao;

    @GetMapping("/")
    public ResponseEntity<Object> getByRequestBodyObject(
            @RequestParam("neighborhoodId") Long neighborhoodId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date date,
            @RequestParam("categories") Long [] categories
    )
    {
        List<InstListQueryRes> response =  institutionDao.readQueryList(neighborhoodId, date, categories);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{institutionId}")
    public ResponseEntity<Object> getById(@PathVariable(value = "institutionId") Long institutionId)
    {
        InstitutionInfoQueryRes response = institutionDao.read(institutionId);

        if(response != null)
            return Utility.createResponse("", response, HttpStatus.OK);
        else
            return Utility.createResponse("", "An error occurred and it has been logged", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
