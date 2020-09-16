package com.engine.promotion.repository;

import com.engine.promotion.entity.ComboPromotionConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComboPromotionRepository extends JpaRepository<ComboPromotionConfig, Long> {
}
