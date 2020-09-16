package com.engine.promotion.repository;

import com.engine.promotion.entity.ComboPromotionConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComboPromotionRepository extends JpaRepository<ComboPromotionConfig, Long> {

    @Query("SELECT CPC " +
            "FROM ComboPromotionConfig CPC " +
            "INNER JOIN FETCH CPC.skus SK ")
    List<ComboPromotionConfig> findPromotionBySKUId();
}
