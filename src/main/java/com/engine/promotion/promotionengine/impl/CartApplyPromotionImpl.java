package com.engine.promotion.promotionengine.impl;

import com.engine.promotion.dto.request.OrderSKUQuantity;
import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.dto.response.SKUPriceDto;
import com.engine.promotion.dto.response.ResponseOrderSKUs;
import com.engine.promotion.entity.*;
import com.engine.promotion.promotionengine.*;
import com.engine.promotion.repository.CartRepository;
import com.engine.promotion.repository.SKURepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartApplyPromotionImpl implements ApplyPromotion {

    @Autowired
    private FixedComboPromotion fixedItemPromotion;

    @Autowired
    private NItemsPromotion nItemPromotion;


    @Autowired
    private SKURepository skuRepository;


    @Override
    public CartPromotionsBO createPromotionBO(RequestOrderSKUs requestOrderSKUs) {
        PromotionConfig comboPromotions = fixedItemPromotion.getDiscountAfterPromotion(requestOrderSKUs).get(0);
        List<PromotionConfig> nItemPromotions = nItemPromotion.getDiscountAfterPromotion(requestOrderSKUs);
        List<OrderSKU> orderSKUS = this.setupCartOrderSKUs(requestOrderSKUs);
        BigDecimal totalCartAmount = this.getCartTotal(orderSKUS);
        CartPromotionsBO cartPromotionsBO = CartPromotionsBO.builder().cartId(requestOrderSKUs.getCartId())
                .fixedComboPromotionList(comboPromotions)
                .nItemsPromotions(nItemPromotions)
                .orderSKUS(orderSKUS)
                .netAmount(totalCartAmount)
                .nonDiscountedSKUs(new ArrayList<SKUPriceDto>())
                .build();
        return cartPromotionsBO;
    }

    private BigDecimal getCartTotal(List<OrderSKU> orderSKUS) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderSKU orderSKU : orderSKUS) {
            Long quantity = orderSKU.getQuantity();
            BigDecimal unitPrice = orderSKU.getUnitPrice();
            total = total.add(unitPrice.multiply(BigDecimal.valueOf(quantity)), MathContext.DECIMAL32);
        }
        return total;
    }

    private List<OrderSKU> setupCartOrderSKUs(RequestOrderSKUs requestOrderSKUs) {
        List<OrderSKU> orderSKUS = new ArrayList<>();
        Map<Long, StockKeepingUnit> skusInventory = skuRepository.findAllById(requestOrderSKUs.getOrderSKUQuantity().stream().map(x -> x.getSkuId()).collect(Collectors.toList()))
                .stream().collect(Collectors.toMap(orderSKU -> orderSKU.getId(), Function.identity()));
        for (OrderSKUQuantity orderSKUQuantity : requestOrderSKUs.getOrderSKUQuantity()) {
            OrderSKU build = OrderSKU.builder()
                    .name(skusInventory.get(orderSKUQuantity.getSkuId()).getName())
                    .quantity(orderSKUQuantity.getQuantity())
                    .unitPrice(skusInventory.get(orderSKUQuantity.getSkuId()).getUnitPrice())
                    .sku(skusInventory.get(orderSKUQuantity.getSkuId())).build();
            orderSKUS.add(build);
        }
        return orderSKUS;
    }


    @Override
    public ResponseOrderSKUs applyPromotions(CartPromotionsBO cartPromotionsBO) {
        List<SKUPriceDto> totalNItemDiscount = this.getTotalNItemDiscount(cartPromotionsBO.getNItemsPromotions(), cartPromotionsBO.getOrderSKUS(), cartPromotionsBO);
        List<SKUPriceDto> totalComboDiscount = this.getTotalComboDiscount(cartPromotionsBO.getFixedComboPromotionList(), cartPromotionsBO.getOrderSKUS(), cartPromotionsBO);
        ResponseOrderSKUs response = new ResponseOrderSKUs();
        response.getOrderSKUQuantity().addAll(totalNItemDiscount);
        response.getOrderSKUQuantity().addAll(totalComboDiscount);
        response.getOrderSKUQuantity().addAll(cartPromotionsBO.getNonDiscountedSKUs());
        BigDecimal netAmount = response.getOrderSKUQuantity().stream().map(SKUPriceDto::getAmount).reduce(BigDecimal::add).get();
        response.setNetAmount(netAmount);
        return response;
    }

    /*
    Lists all the SKU which care eligible of Combined Discount
    Also Lists all the SKU which are left out after Combined Discount is exhausted
     */
    private List<SKUPriceDto> getTotalComboDiscount(PromotionConfig fixedComboProm, List<OrderSKU> orderSKUS, CartPromotionsBO cartPromotionsBO) {
        List<SKUPriceDto> discount = new ArrayList<SKUPriceDto>();
        Map<Long, OrderSKU> orderSKUMap = orderSKUS
                .stream()
                .collect(Collectors.toMap(orderSKU -> orderSKU.getSku().getId(), Function.identity()));
            ComboPromotionConfig comboPromotionConfig = (ComboPromotionConfig) fixedComboProm;
            Set<Long> combineIds = comboPromotionConfig.getSkus().stream().map(x -> x.getId()).collect(Collectors.toSet());
        Set<Long> orderSKUIds = orderSKUMap.entrySet().stream().map(x -> x.getKey()).collect(Collectors.toSet())
                .stream().filter(x -> combineIds.contains(x)).collect(Collectors.toSet());
        Set<String> names = comboPromotionConfig.getSkus().stream().map(x -> x.getName()).collect(Collectors.toSet());
            Boolean comboPromoExhausted = Boolean.FALSE;
        while (orderSKUIds.size() == combineIds.size() && !comboPromoExhausted) {
            for (Long skuId : combineIds) {
                orderSKUMap.get(skuId).setQuantity(orderSKUMap.get(skuId).getQuantity() - 1);
            }
            SKUPriceDto discountSKUQuantity = SKUPriceDto
                    .builder()
                    .isComboOffer(true)
                    .skuId(combineIds)
                    .name(names)
                    .amount(BigDecimal.valueOf(comboPromotionConfig.getCombineFixedPrice().longValue())).build();
            discount.add(discountSKUQuantity);
            Long skusLeft = 0l;
            for (Long skuId : combineIds) {
                if(orderSKUMap.get(skuId).getQuantity() == 0){
                    comboPromoExhausted = Boolean.TRUE;
                    break;
                }
                skusLeft += orderSKUMap.get(skuId).getQuantity();
            }
            if (skusLeft == 0 || skusLeft % combineIds.size() != 0) {
                comboPromoExhausted = Boolean.TRUE;
            }
        }
        //Collect Left out SKUs
        for (Long skuId : combineIds) {
            OrderSKU orderSKU = orderSKUMap.get(skuId);
            if(orderSKU == null){
                continue;
            }
            Long leftQuantity = orderSKU.getQuantity();
            if (leftQuantity > 0) {
                SKUPriceDto leftSKU = SKUPriceDto.builder().isComboOffer(false).quantity(leftQuantity)
                        .skuId(new HashSet<Long>(Arrays.asList(orderSKUMap.get(skuId).getId())))
                        .name(new HashSet<String>(Arrays.asList(orderSKUMap.get(skuId).getName()))).build();
                BigDecimal amount = orderSKUMap.get(skuId).getUnitPrice().multiply(BigDecimal.valueOf(leftQuantity));
                leftSKU.setAmount(amount);
                cartPromotionsBO.getNonDiscountedSKUs().add(leftSKU);
            }
        }
        return discount;
    }

    /*
   Lists all the SKU which care eligible of Pack of N-Items Discount
   Also Lists all the SKU which are left out after N-Items Discount is exhausted
    */

    private List<SKUPriceDto> getTotalNItemDiscount(List<PromotionConfig> nItemsPromotions, List<OrderSKU> orderSKUS, CartPromotionsBO cartPromotionsBO) {
        List<SKUPriceDto> result = new ArrayList<>();
        Map<Long, OrderSKU> orderSKUMap = orderSKUS
                .stream()
                .collect(Collectors.toMap(orderSKU -> orderSKU.getSku().getId(), Function.identity()));
        Map<Long, NItemsPromotionConfig> promotionsSKUOffer = nItemsPromotions
                .stream()
                .map(x -> (NItemsPromotionConfig) x)
                .collect(Collectors.toMap(orderSKU -> orderSKU.getSkus().getId(), Function.identity()));
        Set<Long> skuIds = nItemsPromotions
                .stream()
                .map(x -> (NItemsPromotionConfig) x)
                .map(NItemsPromotionConfig::getSkus)
                .map(StockKeepingUnit::getId)
                .collect(Collectors.toSet());
        for(Long skuId : skuIds){
            SKUPriceDto skuPriceDto = SKUPriceDto.builder().name(Collections.singleton(orderSKUMap.get(skuId).getSku().getName()))
                    .skuId(Collections.singleton(orderSKUMap.get(skuId).getSku().getId()))
                    .fixPackOffer(true)
                    .build();
            Long packQty = promotionsSKUOffer.get(skuId).getQuantity();
            Long orderQty = orderSKUMap.get(skuId).getQuantity();
            Long offerApplyQty = orderQty / packQty;
            BigDecimal offerAmount = promotionsSKUOffer.get(skuId).getFixedPrice().multiply(BigDecimal.valueOf(offerApplyQty));
            skuPriceDto.setAmount(offerAmount);
            skuPriceDto.setQuantity(packQty * offerApplyQty);
            result.add(skuPriceDto);
            //
            Long leftSKUQty = orderQty % packQty;
            if(leftSKUQty > 0){
                SKUPriceDto leftSkuPriceDto = SKUPriceDto.builder().name(Collections.singleton(orderSKUMap.get(skuId).getSku().getName()))
                        .skuId(Collections.singleton(orderSKUMap.get(skuId).getSku().getId()))
                        .fixPackOffer(true)
                        .quantity(leftSKUQty)
                        .amount(orderSKUMap.get(skuId).getSku().getUnitPrice().multiply(BigDecimal.valueOf(leftSKUQty)))
                        .build();
                //Adding left out
                cartPromotionsBO.getNonDiscountedSKUs().add(leftSkuPriceDto);
            }


        }
        return result;
    }
}
