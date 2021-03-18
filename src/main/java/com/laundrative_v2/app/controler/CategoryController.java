package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.beans.db.CategoryDb;
import com.laundrative_v2.app.dao.CategoryDao;
import com.laundrative_v2.app.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/category")
public class CategoryController
{
    @Autowired
    CategoryDao categoryDao;

    @GetMapping(value = "/")
    public ResponseEntity<Object> getAll()
    {
        Iterable<CategoryDb> list = categoryDao.readAll();
        return Utility.createResponse("", list, HttpStatus.OK);
    }
}
