package com.laundrative_v1.app.repository;

import com.laundrative_v1.app.dao.StoreDao;
import com.laundrative_v1.app.entity.StoreEntity;

import java.util.Optional;

public interface StoreRepo extends CrudRepo<StoreEntity, Long>
{
}
