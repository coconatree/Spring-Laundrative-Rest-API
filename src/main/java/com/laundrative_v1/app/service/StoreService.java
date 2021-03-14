package com.laundrative_v1.app.service;

import com.laundrative_v1.app.dao.StoreDao;
import com.laundrative_v1.app.entity.StoreEntity;
import com.laundrative_v1.app.repository.StoreRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<StoreEntity> readAll()
    {
        return repo.findAll();
    }

    public void update(){}
    public void delete(){}

}
