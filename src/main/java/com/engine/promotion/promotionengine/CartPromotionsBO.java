package com.engine.promotion.promotionengine;

import com.engine.promotion.entity.OrderSKU;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Slf4j
@Builder
public class CartPromotionsBO {

    private Long cartId;
    private List<FixedComboPromotion> fixedComboPromotionList;
    private List<NItemsPromotion> nItemsPromotions;
    private List<OrderSKU> orderSKUS;
    private BigDecimal discountAmount;
    private BigDecimal cartTotalAmount;
    private BigDecimal netAmount;


}
