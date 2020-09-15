package com.engine.promotion.repository;

import com.engine.promotion.entity.StockKeepingUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SKURepository extends JpaRepository<StockKeepingUnit, Long> {
}
