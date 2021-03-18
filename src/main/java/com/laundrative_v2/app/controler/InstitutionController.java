package com.laundrative_v2.app.controler;

import com.laundrative_v2.app.dao.InstitutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/institution") // Url with /user/** will be set as USER role in spring security
public class InstitutionController
{
    @Autowired
    InstitutionDao institutionDao;

    @GetMapping("/")
    public Iterable<com.laundrative_v2.app.beans.db.InstitutionDb> getAll()
    {
        return institutionDao.readAll();
    }

}
