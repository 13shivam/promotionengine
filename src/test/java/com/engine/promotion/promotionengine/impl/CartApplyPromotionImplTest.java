package com.engine.promotion.promotionengine.impl;

import com.engine.promotion.TestScenario1;
import com.engine.promotion.TestScenario2;
import com.engine.promotion.TestScenario3;
import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.dto.response.ResponseOrderSKUs;
import com.engine.promotion.promotionengine.CartPromotionsBO;
import com.engine.promotion.promotionengine.FixedComboPromotion;
import com.engine.promotion.promotionengine.NItemsPromotion;
import com.engine.promotion.repository.SKURepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartApplyPromotionImplTest {

    @InjectMocks
    CartApplyPromotionImpl cartApplyPromotion;

    @Mock
    private FixedComboPromotion fixedComboPromotion;

    @Mock
    private NItemsPromotion nItemsPromotion;


    @Mock(lenient = true)
    private SKURepository skuRepository;

    private static CartPromotionsBO cartPromotionsBO;

    //Scenario 1
    @Test
    void Scenario1_X() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        RequestOrderSKUs requestScenario1 = TestScenario1.getRequestScenario1();
        Mockito.when(fixedComboPromotion.getDiscountAfterPromotion(requestScenario1))
                .thenReturn(TestScenario1.getPromotionConfigScenario1());
        Mockito.when(nItemsPromotion.getDiscountAfterPromotion(requestScenario1))
                .thenReturn(TestScenario1.getNthConfigScenario1());
        Mockito.when(skuRepository.findAllById(requestScenario1.getOrderSKUQuantity().stream().map(x -> x.getSkuId()).collect(Collectors.toList())))
                .thenReturn(TestScenario1.skuRepoResponse());
        CartPromotionsBO promotionBO = cartApplyPromotion.createPromotionBO(requestScenario1);
        cartPromotionsBO = promotionBO;
        Assert.assertEquals("Total Cart Amount is 100", BigDecimal.valueOf(100l), promotionBO.getNetAmount());
    }

    @Test
    void Scenario1_Y() {
        MockitoAnnotations.initMocks(this);
        ResponseOrderSKUs responseOrderSKUs = cartApplyPromotion.applyPromotions(cartPromotionsBO);
        Assert.assertEquals("Discount Cart Amount is 100", BigDecimal.valueOf(100l), responseOrderSKUs.getNetAmount());
    }

    //Scenario 2
    @Test
    void Scenario2_X() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        RequestOrderSKUs requestScenario2 = TestScenario2.getRequestScenario2();
        Mockito.when(fixedComboPromotion.getDiscountAfterPromotion(requestScenario2))
                .thenReturn(TestScenario2.getPromotionConfigScenario2());
        Mockito.when(nItemsPromotion.getDiscountAfterPromotion(requestScenario2))
                .thenReturn(TestScenario2.getNthConfigScenario2());
        Mockito.when(skuRepository.findAllById(requestScenario2.getOrderSKUQuantity().stream().map(x -> x.getSkuId()).collect(Collectors.toList())))
                .thenReturn(TestScenario2.skuRepoResponse());
        CartPromotionsBO promotionBO = cartApplyPromotion.createPromotionBO(requestScenario2);
        cartPromotionsBO = promotionBO;
        Assert.assertEquals("Total Cart Amount is 420", BigDecimal.valueOf(420l), promotionBO.getNetAmount());
    }

    @Test
    void Scenario2_Y() {
        MockitoAnnotations.initMocks(this);
        ResponseOrderSKUs responseOrderSKUs = cartApplyPromotion.applyPromotions(cartPromotionsBO);
        Assert.assertEquals("Discount Cart Amount is 370", BigDecimal.valueOf(370l), responseOrderSKUs.getNetAmount());
    }

    //Scenario 3
    @Test
    void Scenario3_X() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        RequestOrderSKUs requestScenario3 = TestScenario3.getRequestScenario3();
        Mockito.when(fixedComboPromotion.getDiscountAfterPromotion(requestScenario3))
                .thenReturn(TestScenario3.getPromotionConfigScenario3());
        Mockito.when(nItemsPromotion.getDiscountAfterPromotion(requestScenario3))
                .thenReturn(TestScenario3.getNthConfigScenario3());
        Mockito.when(skuRepository.findAllById(requestScenario3.getOrderSKUQuantity().stream().map(x -> x.getSkuId()).collect(Collectors.toList())))
                .thenReturn(TestScenario3.skuRepoResponse());
        CartPromotionsBO promotionBO = cartApplyPromotion.createPromotionBO(requestScenario3);
        cartPromotionsBO = promotionBO;
        Assert.assertEquals("Total Cart Amount is 335", BigDecimal.valueOf(335l), promotionBO.getNetAmount());
    }

    @Test
    void Scenario3_Y() {
        MockitoAnnotations.initMocks(this);
        ResponseOrderSKUs responseOrderSKUs = cartApplyPromotion.applyPromotions(cartPromotionsBO);
        Assert.assertEquals("Discount Cart Amount is 280", BigDecimal.valueOf(280l), responseOrderSKUs.getNetAmount());
        System.out.println("--------------------------------Scenario-3-------------------------------------------------");
        System.out.println("Before Discount Cart Value:" + cartPromotionsBO.getNetAmount());
        System.out.println("After Discount Cart Value:" + responseOrderSKUs.getNetAmount());
        System.out.println("--------------------------------Scenario-3-------------------------------------------------");

    }
}