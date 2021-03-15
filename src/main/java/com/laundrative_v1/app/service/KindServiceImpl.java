package com.laundrative_v1.app.service;

import com.laundrative_v1.app.dao.KindDao;
import com.laundrative_v1.app.dao.TypeDao;
import com.laundrative_v1.app.entity.KindEntity;
import com.laundrative_v1.app.entity.TypeEntity;
import com.laundrative_v1.app.repository.KindRepo;
import com.laundrative_v1.app.serviceI.IKindService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@NoArgsConstructor
public class KindServiceImpl implements IKindService
{
    @Autowired
    KindRepo repo;

    private static final Logger logger = LoggerFactory.getLogger(KindServiceImpl.class);

    @Override
    public KindDao read(Long id)
    {
        try
        {
            if(repo.existsById(id))
                return new KindDao(repo.findById(id).get());
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
    public List<KindDao> readAll()
    {
        List<KindDao> list = null;
        try
        {
            list = new ArrayList<>();

            for (KindEntity entity : repo.findAll()){ list.add(new KindDao(entity)); }

            if(list.size() == 0)
                return null;
            else
                return list;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the StoreServiceImpl", e);
            return null;
        }
    }
}
