package com.laundrative_v1.app.serviceI;

import com.laundrative_v1.app.dao.StoreDao;

import java.util.List;

public interface IStoreService
{
    StoreDao read(Long id);
    StoreDao create(StoreDao obj);
    List<StoreDao> readAll();
}
