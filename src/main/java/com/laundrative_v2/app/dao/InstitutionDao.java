package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.InstitutionDb;
import com.laundrative_v2.app.repository.InstitutionRepo;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionDao
{
    private static final Logger logger = LoggerFactory.getLogger(InstitutionDao.class);

    @Autowired
    private InstitutionRepo repo;

    public List<InstitutionDb> readAll()
    {
        try
        {
            return repo.findAll();
        }
        catch (Exception e)
        {
            logger.warn(String.valueOf("Error cause : \n " + e.getCause()));
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn(String.valueOf("Error stack trace : \n " + e.getStackTrace()));

            return null;
        }
    }

    public InstitutionDb read(Long institutionId)
    {
        try
        {
            return repo.findById(institutionId).get();
        }
        catch (Exception e)
        {
            logger.warn(String.valueOf("Error cause : \n " + e.getCause()));
            logger.warn("Error message : \n " + e.getMessage());
            logger.warn(String.valueOf("Error stack trace : \n " + e.getStackTrace()));

            return null;
        }
    }
}
