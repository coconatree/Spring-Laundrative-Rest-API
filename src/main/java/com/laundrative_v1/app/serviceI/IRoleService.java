package com.laundrative_v1.app.serviceI;

import com.laundrative_v1.app.dao.RoleDao;

import java.util.List;

public interface IRoleService
{
    RoleDao read(Long id);
    List<RoleDao> readAll();
}
