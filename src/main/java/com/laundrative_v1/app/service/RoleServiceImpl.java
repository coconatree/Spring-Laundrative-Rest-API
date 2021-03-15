package com.laundrative_v1.app.service;

import com.laundrative_v1.app.dao.RoleDao;
import com.laundrative_v1.app.entity.RoleEntity;
import com.laundrative_v1.app.repository.RoleRepo;
import com.laundrative_v1.app.serviceI.IRoleService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class RoleServiceImpl implements IRoleService
{
    private RoleRepo repo;
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    public RoleServiceImpl(RoleRepo repo)
    {
        this.repo = repo;
    }

    @Override
    public RoleDao read(Long id)
    {
        try
        {
            if(repo.existsById(id))
                return new RoleDao(repo.findById(id).get());
            else
                return null;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the RoleServiceImpl", e);
            return null;
        }
    }

    @Override
    public List<RoleDao> readAll()
    {
        List<RoleDao> list;

        try
        {
            list = new ArrayList<>();

            for (RoleEntity entity : repo.findAll()) { list.add(new RoleDao(entity)); }

            if(list.size() == 0)
                return null;
            else
                return list;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the RoleServiceImpl", e);
            return null;
        }
    }
}
