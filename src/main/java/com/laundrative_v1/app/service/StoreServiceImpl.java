package com.laundrative_v1.app.service;

import com.laundrative_v1.app.dao.StoreDao;
import com.laundrative_v1.app.entity.StoreEntity;
import com.laundrative_v1.app.repository.StoreRepo;
import com.laundrative_v1.app.serviceI.IStoreService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class StoreServiceImpl implements IStoreService
{
    @Autowired
    StoreRepo repo;

    @Override
    public StoreDao read(Long id)
    {
        return new StoreDao(repo.findById(id).get());
    }

    @Override
    public List<StoreDao> readAll()
    {
        List<StoreDao> list = new ArrayList<>();

        for (StoreEntity entity : repo.findAll())
        {
            list.add(new StoreDao(entity));
        }
        return list;
    }

    @Override
    public StoreDao create(StoreDao obj)
    {
        StoreEntity entity = StoreEntity.builder()
                .storeName(obj.getStoreName())
                .telephone(obj.getTelephone())
                .supervisorName(obj.getSupervisorName())
                .email(obj.getEmail())
                .build();

        return new StoreDao(repo.save(entity));
    }
}
