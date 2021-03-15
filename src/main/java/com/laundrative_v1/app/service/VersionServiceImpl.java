package com.laundrative_v1.app.service;

import com.laundrative_v1.app.dao.VersionDao;
import com.laundrative_v1.app.entity.VersionEntity;
import com.laundrative_v1.app.repository.VersionRepo;
import com.laundrative_v1.app.serviceI.IVersionService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class VersionServiceImpl implements IVersionService
{
    private VersionRepo repo;

    private static final Logger logger = LoggerFactory.getLogger(VersionServiceImpl.class);

    @Autowired
    public VersionServiceImpl(VersionRepo repo) {
        this.repo = repo;
    }

    @Override
    public VersionDao read(Long id)
    {
        try
        {
            if(repo.existsById(id))
                return new VersionDao(repo.findById(id).get());
            else
                return null;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the VersionServiceImpl", e);
            return null;
        }
    }

    @Override
    public List<VersionDao> readAll()
    {
        List<VersionDao> list;

        try
        {
            list = new ArrayList<>();

            for (VersionEntity entity : repo.findAll()) { list.add(new VersionDao(entity)); }

            if(list.size() == 0)
                return null;
            else
                return list;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the VersionServiceImpl", e);
            return null;
        }
    }
}
