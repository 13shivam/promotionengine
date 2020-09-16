package com.engine.promotion.promotionengine.impl;

import com.engine.promotion.TestScenario1;
import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.promotionengine.CartPromotionsBO;
import com.engine.promotion.promotionengine.FixedComboPromotion;
import com.engine.promotion.promotionengine.NItemsPromotion;
import com.engine.promotion.repository.SKURepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@RunWith(MockitoJUnitRunner.class)
public class CartApplyPromotionImplTest {

    @InjectMocks
    CartApplyPromotionImpl cartApplyPromotion;

    @Mock
    private FixedComboPromotion fixedComboPromotion;

    @Mock
    private NItemsPromotion nItemsPromotion;


    @Mock(lenient = true)
    private SKURepository skuRepository;

    @Test
    void createPromotionBO_Scenario1() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        RequestOrderSKUs requestScenario1 = TestScenario1.getRequestScenario1();
        Mockito.when(fixedComboPromotion.getDiscountAfterPromotion(requestScenario1))
                .thenReturn(TestScenario1.getPromotionConfigScenario1());
        Mockito.when(nItemsPromotion.getDiscountAfterPromotion(requestScenario1))
                .thenReturn(TestScenario1.getNthConfigScenario1());
        Mockito.when(skuRepository.findAllById(requestScenario1.getOrderSKUQuantity().stream().map(x -> x.getSkuId()).collect(Collectors.toList())))
                .thenReturn(TestScenario1.skuRepoResponse());
        CartPromotionsBO promotionBO = cartApplyPromotion.createPromotionBO(requestScenario1);
        Assert.assertEquals("Total Cart Amount is 100", BigDecimal.valueOf(100l), promotionBO.getNetAmount());
        System.out.println("Total Amount "+promotionBO.getNetAmount());
    }

    @Test
    void applyPromotions() {
    }
}