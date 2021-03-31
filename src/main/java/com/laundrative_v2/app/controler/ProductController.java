package com.laundrative_v2.app.controler;


import com.laundrative_v2.app.beans.json.Response.ProductRes;
import com.laundrative_v2.app.dao.ProductDao;
import com.laundrative_v2.app.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductDao productDao;

    @GetMapping(value = "/")
    public ResponseEntity<Object> getProduct(@RequestParam(value = "productName") String productName)
    {
        logger.warn("Product name : " + productName);

        List<ProductRes> responses = productDao.searchByName(productName);

        if (responses == null)
            return Utility.createResponse("", null, HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return Utility.createResponse("", responses, HttpStatus.OK);
    }
}
