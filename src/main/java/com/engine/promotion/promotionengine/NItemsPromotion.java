package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.entity.NItemsPromotionConfig;
import com.engine.promotion.entity.PromotionConfig;
import com.engine.promotion.repository.NItemsPromotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("NItemsPromotion")
public class NItemsPromotion implements Promotion {

    @Autowired
    private NItemsPromotionRepository nItemsPromotionRepository;

    //Applied for N-items promotions
    @Override
    public List<PromotionConfig> getDiscountAfterPromotion(RequestOrderSKUs requestOrderSKUs) {
        List<Long> skuIds = requestOrderSKUs.getOrderSKUQuantity().stream().map(orderSKUs -> orderSKUs.getSkuId()).collect(Collectors.toList());
        List<NItemsPromotionConfig> nItemsPromotionConfigs = nItemsPromotionRepository.fetchNItemsPromotions(skuIds);
        List<PromotionConfig> results = nItemsPromotionConfigs.stream().map(x -> (PromotionConfig) x).collect(Collectors.toList());
        return results;
    }
}
