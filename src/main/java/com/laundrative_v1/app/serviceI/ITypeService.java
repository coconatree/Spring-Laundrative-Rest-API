package com.laundrative_v1.app.serviceI;

import com.laundrative_v1.app.dao.TypeDao;

import java.util.List;

public interface ITypeService
{
    TypeDao read(Long id);
    List<TypeDao> readAll();
}
