package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.InstitutionDb;
import com.laundrative_v2.app.repository.InstitutionRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionDao
{
    @Autowired
    private InstitutionRepo repo;

    public Iterable<InstitutionDb> readAll()
    {
        return repo.findAll();
    }
}
