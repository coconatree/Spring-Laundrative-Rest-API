package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.repository.KindRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KindDao
{
    @Autowired
    KindRepo repo;

    public KindDb read(Long id, Long category)
    {
        KindDb obj = repo.findByIdAndCategory(id, category);

        if(obj == null)
            return null;

        return obj;
    }

    public Iterable<KindDb> readAll()
    {
        return repo.findAll();
    }
}
