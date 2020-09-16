package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.entity.ComboPromotionConfig;
import com.engine.promotion.entity.PromotionConfig;
import com.engine.promotion.repository.ComboPromotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("FixedComboPromotion")
public class FixedComboPromotion implements Promotion {

    @Autowired
    private ComboPromotionRepository comboPromotionRepository;

    //Applied for Combo SKUs (A+B) promotions
    @Override
    public List<PromotionConfig> getDiscountAfterPromotion(RequestOrderSKUs requestOrderSKUs) {
        List<Long> skuIds = requestOrderSKUs.getOrderSKUQuantity().stream().map(orderSKUs -> orderSKUs.getSkuId()).collect(Collectors.toList());
        ComboPromotionConfig promotionBySKUId = comboPromotionRepository.findPromotionBySKUId(skuIds);
        return Arrays.asList(promotionBySKUId);
    }

}
