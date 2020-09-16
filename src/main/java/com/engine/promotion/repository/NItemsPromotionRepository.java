package com.engine.promotion.repository;

import com.engine.promotion.entity.NItemsPromotionConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NItemsPromotionRepository extends JpaRepository<NItemsPromotionConfig, Long> {

    @Query("SELECT NIPC " +
            "FROM NItemsPromotionConfig NIPC " +
            "INNER JOIN FETCH NIPC.skus SK " +
            "WHERE SK.id IN (:skuIds) ")
    List<NItemsPromotionConfig> fetchNItemsPromotions(List<Long> skuIds);
}
