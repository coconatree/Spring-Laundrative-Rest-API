package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.PriceDb;
import com.laundrative_v2.app.beans.json.CategoryKindTypeJson;
import com.laundrative_v2.app.dao.PriceDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/price")
public class PriceController
{
    @Autowired
    PriceDao priceDao;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable(value = "id")  Long id)
    {
        return Utility.createResponse("", priceDao.get(id), HttpStatus.OK);
    }

    /**

     Accepts a json object which has the following format;

     {
     "category":"$value",
     "kind":"$value",
     "type":"$value"
     }

     */

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get(@RequestBody CategoryKindTypeJson obj)
    {
        PriceDb priceDb = priceDao.get(obj.getCategory(), obj.getKind(), obj.getKind());

        if(priceDao == null)
        {
            return Utility.createResponse("", null, HttpStatus.BAD_REQUEST);
        }
        return Utility.createResponse("", priceDb, HttpStatus.OK);
    }
}
