package com.laundrative_v1.app.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepo<T, ID extends Serializable> extends JpaRepository
{

}
