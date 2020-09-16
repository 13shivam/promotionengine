package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.dto.response.ResponseOrderSKUs;

public interface ApplyPromotion {

    ResponseOrderSKUs applyPromotions(CartPromotionsBO cartPromotionsBO);

    CartPromotionsBO createPromotionBO(RequestOrderSKUs requestOrderSKUs);

}
