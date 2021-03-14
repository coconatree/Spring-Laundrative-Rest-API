package com.laundrative_v1.app.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface CrudRepo<T, ID extends Serializable> extends BaseRepo<T, ID>
{
    @Transactional
    T findById(ID id);

    @Transactional
    T findAllById(ID id);

    @Transactional
    boolean existsById(ID id);

    @Transactional
    List<T> findAll();
}
