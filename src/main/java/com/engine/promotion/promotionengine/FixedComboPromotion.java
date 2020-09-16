package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.OrderSKUQuantity;
import com.engine.promotion.dto.response.DiscountSKUQuantity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixedComboPromotion implements Promotion {

    //Applied for Combo SKUs (A+B) promotions
    @Override
    public DiscountSKUQuantity getDiscountAfterPromotion(OrderSKUQuantity orderSKUQuantity) {
        return null;
    }

}
