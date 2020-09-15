package com.engine.promotion.repository;

import com.engine.promotion.entity.StockKeepingUnitPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SKUPriceRepository extends JpaRepository<StockKeepingUnitPrice, Long> {
}
