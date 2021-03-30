package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.OrderDetailsDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepo extends CrudRepository<OrderDetailsDb, Long>
{
    @Override
    <S extends OrderDetailsDb> Iterable<S> saveAll(Iterable<S> entities);
}
