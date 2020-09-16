package com.engine.promotion.repository;

import com.engine.promotion.entity.NItemsPromotionConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NItemsPromotionRepository extends JpaRepository<NItemsPromotionConfig, Long> {
}
