package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.CategoryDb;
import com.laundrative_v2.app.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryDao
{
    @Autowired
    CategoryRepo repo;

    public Iterable<CategoryDb> readAll()
    {
        return repo.findAll();
    }

}
