package com.laundrative_v1.app.serviceI;

import com.laundrative_v1.app.dao.KindDao;

import java.util.List;

public interface IKindService
{
    KindDao read(Long id);
    List<KindDao> readAll();
}
