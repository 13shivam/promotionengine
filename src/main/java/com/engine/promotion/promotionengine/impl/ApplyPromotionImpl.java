package com.engine.promotion.promotionengine.impl;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.dto.response.DiscountSKUQuantity;
import com.engine.promotion.dto.response.ResponseOrderSKUs;
import com.engine.promotion.entity.ComboPromotionConfig;
import com.engine.promotion.entity.NItemsPromotionConfig;
import com.engine.promotion.entity.PromotionConfig;
import com.engine.promotion.promotionengine.ApplyPromotion;
import com.engine.promotion.promotionengine.CartPromotionsBO;
import com.engine.promotion.promotionengine.Promotion;
import com.engine.promotion.repository.ComboPromotionRepository;
import com.engine.promotion.repository.NItemsPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyPromotionImpl implements ApplyPromotion {

    @Autowired
    @Qualifier("FixedComboPromotion")
    private Promotion fixedItemPromotion;

    @Autowired
    @Qualifier("NItemsPromotion")
    private Promotion nItemPromotion;

    @Autowired
    private ComboPromotionRepository comboPromotionRepository;

    @Override
    public CartPromotionsBO createPromotionBO(RequestOrderSKUs requestOrderSKUs) {
        List<PromotionConfig> comboPromotions = fixedItemPromotion.getDiscountAfterPromotion(requestOrderSKUs);
        List<PromotionConfig> nItemPromotions = nItemPromotion.getDiscountAfterPromotion(requestOrderSKUs);
        return null;
    }


    @Override
    public ResponseOrderSKUs applyPromotions(CartPromotionsBO cartPromotionsBO) {
        return null;
    }
}
