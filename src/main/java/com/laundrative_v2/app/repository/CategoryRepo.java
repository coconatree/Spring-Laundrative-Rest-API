package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.CategoryDb;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<CategoryDb, Long> { }
