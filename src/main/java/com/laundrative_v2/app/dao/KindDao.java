package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.repository.KindRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindDao
{
    @Autowired
    KindRepo repository;

    public KindDb findByIdAndCategoryId(Long id, Long categoryId, String param)
    {
        return repository.searchByIdAndCategoryAndParam(id, categoryId, "%" + param + "%");
    }
}
