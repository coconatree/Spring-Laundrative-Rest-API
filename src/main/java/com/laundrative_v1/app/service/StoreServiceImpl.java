package com.laundrative_v1.app.service;

import com.laundrative_v1.app.dao.StoreDao;
import com.laundrative_v1.app.entity.StoreEntity;
import com.laundrative_v1.app.repository.StoreRepo;
import com.laundrative_v1.app.serviceI.IStoreService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
    Following is the implementation of the IService interface
 */

@Service
@NoArgsConstructor
public class StoreServiceImpl implements IStoreService
{
    @Autowired
    StoreRepo repo;

    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    @Override
    public StoreDao read(Long id)
    {
        try
        {
            if(repo.existsById(id))
                return new StoreDao(repo.findById(id).get());
            else
                return null;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the StoreServiceImpl", e);
            return null;
        }
    }

    @Override
    public List<StoreDao> readAll()
    {
        List<StoreDao> list = null;

        try
        {
            list = new ArrayList<>();

            for (StoreEntity entity : repo.findAll()){ list.add(new StoreDao(entity)); }

            if(list.size() == 0)
                return null;
            else
                return list;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the StoreServiceImpl", e);
            e.printStackTrace();
            return null;
        }
    }

    /**

     Following POST url will not be available for the mobile app in the production version

     */

    @Override
    public StoreDao create(StoreDao obj)
    {
        try
        {
            StoreEntity entity = StoreEntity.builder()
                    .storeName(obj.getStoreName())
                    .telephone(obj.getTelephone())
                    .supervisorName(obj.getSupervisorName())
                    .email(obj.getEmail())
                    .build();

            return new StoreDao(repo.save(entity));
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the StoreServiceImpl", e);
            return null;
        }
    }
}
