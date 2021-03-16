package com.laundrative_v2.app.repository;

import com.laundrative_v2.app.beans.db.PriceDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<PriceDb, Long>
{
    PriceDb findByCategoryAndKindAndType(Long category, Long kind, Long type);
}
