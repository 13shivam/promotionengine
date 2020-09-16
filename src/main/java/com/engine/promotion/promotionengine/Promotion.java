package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.entity.PromotionConfig;

import java.util.List;

public interface Promotion {

    List<PromotionConfig> getDiscountAfterPromotion(RequestOrderSKUs orderSKUQuantity);
}
