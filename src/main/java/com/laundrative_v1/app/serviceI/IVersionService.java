package com.laundrative_v1.app.serviceI;

import com.laundrative_v1.app.dao.VersionDao;

import java.util.List;

public interface IVersionService
{
    VersionDao read(Long id);
    List<VersionDao> readAll();
}
