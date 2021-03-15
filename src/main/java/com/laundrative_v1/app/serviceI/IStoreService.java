package com.laundrative_v1.app.serviceI;

import com.laundrative_v1.app.dao.StoreDao;

import java.util.List;

/**
    This is the interface for the StoreService class it creates a template to follow in the service class
 */

public interface IStoreService
{
    StoreDao read(Long id);
    List<StoreDao> readAll();
    StoreDao create(StoreDao obj);
}
