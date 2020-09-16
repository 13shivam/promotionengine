package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.dto.response.DiscountSKUQuantity;

import java.util.List;

public interface Promotion {

    List<DiscountSKUQuantity> getDiscountAfterPromotion(RequestOrderSKUs orderSKUQuantity);
}
