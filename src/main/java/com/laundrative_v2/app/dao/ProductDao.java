package com.laundrative_v2.app.dao;
import com.laundrative_v2.app.beans.json.Response.ProductRes;
import com.laundrative_v2.app.repository.KindRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDao
{
    @Autowired
    KindRepo kindRepo;

    public List<ProductRes> searchByName(String name)
    {
        return kindRepo.findAllByName(name).stream().map(ProductRes::from).collect(Collectors.toList());
    };

}
