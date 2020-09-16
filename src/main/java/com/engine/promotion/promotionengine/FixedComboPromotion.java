package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.dto.response.DiscountSKUQuantity;
import com.engine.promotion.repository.ComboPromotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component("FixedComboPromotion")
public class FixedComboPromotion implements Promotion {

    @Autowired
    private ComboPromotionRepository comboPromotionRepository;

    //Applied for Combo SKUs (A+B) promotions
    @Override
    public List<DiscountSKUQuantity> getDiscountAfterPromotion(RequestOrderSKUs requestOrderSKUs) {
        return null;
    }

}
