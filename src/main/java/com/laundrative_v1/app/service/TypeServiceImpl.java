package com.laundrative_v1.app.service;

import com.laundrative_v1.app.dao.TypeDao;
import com.laundrative_v1.app.entity.TypeEntity;
import com.laundrative_v1.app.repository.TypeRepo;
import com.laundrative_v1.app.serviceI.ITypeService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class TypeServiceImpl implements ITypeService
{
    @Autowired
    TypeRepo repo;

    private static final Logger logger = LoggerFactory.getLogger(TypeServiceImpl.class);

    @Deprecated
    @Override
    public TypeDao read(Long id)
    {
        try
        {
            if(repo.findById(id).isPresent())
                return new TypeDao(repo.findById(id).get());
            else
                return null;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the TypeServiceImpl", e);
            return null;
        }
    }

    @Override
    public List<TypeDao> readAll()
    {
        List<TypeDao> list = null;

        try
        {
            list = new ArrayList<>();

            for (TypeEntity entity : repo.findAll()){ list.add(new TypeDao(entity)); }

            if(list.size() == 0)
                return null;
            else
                return list;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the TypeService", e);
            return null;
        }
    }
}
