package com.laundrative_v1.app.service;

import com.laundrative_v1.app.dao.TypeDao;
import com.laundrative_v1.app.dao.UserDao;
import com.laundrative_v1.app.entity.UserEntity;
import com.laundrative_v1.app.repository.UserRepo;
import com.laundrative_v1.app.serviceI.IUserService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserServiceImpl implements IUserService
{
    UserRepo repo;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDao read(Long id)
    {
        try
        {
            if(repo.existsById(id))
                return new UserDao((repo.findById(id).get()));
            else
                return null;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the UserServiceImpl", e);
            return null;
        }
    }

    @Override
    public UserDao create(UserDao obj)
    {
        // This method need to be written in a better way !!!

        UserDao userDao = null;

        try
        {
            UserEntity entity = UserEntity.builder()
                    .institutionId(obj.getInstitutionId())
                    .email(obj.getEmail())
                    .username(obj.getUsername())
                    .password(obj.getPassword())
                    .role(obj.getRole()) // This will be the default user role
                    .secretKey("") // This will be generated via the rest api and set
                    .build();

            // Automatically check for unique keys but validation for other properties must be done while creating a new row inside the database !

            // There will be validations with email checking and etc ...

            userDao= new UserDao(repo.save(entity));

            if(userDao == null)
                return null;
            else
                return userDao;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the UserServiceImpl", e);
            return null;
        }
    }

    @Override
    public UserDao update()
    {
        try
        {
            return null;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the UserServiceImpl", e);
            return null;
        }
    }

    @Override
    public UserDao delete()
    {
        try
        {
            return null;
        }
        catch (Exception e)
        {
            logger.warn("Exception appeared in the UserServiceImpl", e);
            return null;
        }
    }
}
