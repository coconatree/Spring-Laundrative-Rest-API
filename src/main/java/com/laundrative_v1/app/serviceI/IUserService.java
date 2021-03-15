package com.laundrative_v1.app.serviceI;

import com.laundrative_v1.app.dao.UserDao;

public interface IUserService
{
    UserDao read(Long id);
    UserDao create(UserDao obj);
    UserDao update();
    UserDao delete();
}
