package com.laundrative_v2.app.service;

import com.laundrative_v2.app.beans.db.KindDb;
import com.laundrative_v2.app.dao.KindDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindService
{
    @Autowired
    KindDao kindDao;

    public void findProductByIdAndCategoryId(Long id, Long categoryId){}

    public KindDb findProductsByIdAndCategoryId(Long id, Long categoryId, String param)
    {
        return kindDao.findByIdAndCategoryId(id, categoryId, param);
    }
}
