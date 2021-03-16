package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.TypeDb;
import com.laundrative_v2.app.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeDao
{
    @Autowired
    TypeRepo typeRepo;

    public Iterable<TypeDb> readALl()
    {
        return typeRepo.findAll();
    }
}
