package com.laundrative_v1.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CrudRepo<T, ID extends Serializable> extends CrudRepository<T, ID>
{ }
