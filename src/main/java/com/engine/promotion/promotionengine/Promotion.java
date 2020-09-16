package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.OrderSKUQuantity;
import com.engine.promotion.dto.response.DiscountSKUQuantity;

public interface Promotion {

    DiscountSKUQuantity getDiscountAfterPromotion(OrderSKUQuantity orderSKUQuantity);
}
