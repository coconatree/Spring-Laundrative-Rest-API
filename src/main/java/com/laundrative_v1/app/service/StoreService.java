package com.laundrative_v1.app.service;

import com.laundrative_v1.app.entity.StoreEntity;
import com.laundrative_v1.app.repository.StoreRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class StoreService
{
    @Autowired
    StoreRepo repo;

    public void create()
    {

    }

    public void read()
    {

    }

    public Optional<StoreEntity> readAll()
    {
        Long id = new Long(1);

        return repo.findById(id);
    }

    public void update(){}
    public void delete(){}

}
