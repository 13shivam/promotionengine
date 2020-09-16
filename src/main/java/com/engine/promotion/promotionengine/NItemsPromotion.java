package com.engine.promotion.promotionengine;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.dto.response.DiscountSKUQuantity;
import com.engine.promotion.repository.NItemsPromotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("NItemsPromotion")
public class NItemsPromotion implements Promotion {

    @Autowired
    private NItemsPromotionRepository nItemsPromotionRepository;

    //Applied for N-items promotions
    @Override
    public List<DiscountSKUQuantity> getDiscountAfterPromotion(RequestOrderSKUs requestOrderSKUs) {
        return null;
    }
}
