package com.laundrative_v2.app.dao;

import com.laundrative_v2.app.beans.db.PriceDb;
import com.laundrative_v2.app.repository.PriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceDao
{
    @Autowired
    PriceRepo priceRepo;

    public PriceDb get(Long id)
    {
        return priceRepo.getOne(id);
    }

    public PriceDb get(Long category, Long kind, Long type)
    {
        return priceRepo.findByCategoryAndKindAndType(category, kind, type);
    }
}
