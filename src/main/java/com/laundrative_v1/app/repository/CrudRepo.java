package com.laundrative_v1.app.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface CrudRepo<T, ID extends Serializable> extends BaseRepo<T, ID>
{
    T findById(ID id);
    T findAllById(ID id);

    boolean existsById(ID id);

    List<T> findAll();
}
