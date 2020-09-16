package com.engine.promotion.init;

import com.engine.promotion.entity.ComboPromotionConfig;
import com.engine.promotion.entity.NItemsPromotionConfig;
import com.engine.promotion.entity.PromotionConfig;
import com.engine.promotion.entity.StockKeepingUnit;
import com.engine.promotion.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class InitService {

    /*
    Setting up Database entries for Demo Project
     */
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ComboPromotionRepository comboPromotionRepository;

    @Autowired
    private NItemsPromotionRepository nItemsPromotionRepository;

    @Autowired
    private SKURepository skuRepository;

    @PostConstruct
    public void init() {
        this.setupSKUs();
        this.setupPromotionConfig();
    }

    private void setupSKUs() {
        StockKeepingUnit A = StockKeepingUnit.builder().id(1l).name("A").unitPrice(BigDecimal.valueOf(50)).quantity(10l).build();
        StockKeepingUnit B = StockKeepingUnit.builder().id(2l).name("B").unitPrice(BigDecimal.valueOf(30)).quantity(10l).build();
        StockKeepingUnit C = StockKeepingUnit.builder().id(3l).name("C").unitPrice(BigDecimal.valueOf(20)).quantity(10l).build();
        StockKeepingUnit D = StockKeepingUnit.builder().id(4l).name("D").unitPrice(BigDecimal.valueOf(15)).quantity(10l).build();
        StockKeepingUnit E = StockKeepingUnit.builder().id(5l).name("E").unitPrice(BigDecimal.valueOf(25)).quantity(10l).build();
        StockKeepingUnit F = StockKeepingUnit.builder().id(6l).name("F").unitPrice(BigDecimal.valueOf(35)).quantity(10l).build();
        this.skuRepository.saveAll(Arrays.asList(A, B, C, D, E, F));
    }

    private void setupPromotionConfig() {
        //Setting COMBO Promotions
        List<StockKeepingUnit> allById = skuRepository.findAllById(Arrays.asList(3l, 4l));
        ComboPromotionConfig comboPromotionConfig = ComboPromotionConfig.builder().
                combineFixedPrice(BigDecimal.valueOf(30)).id(1l).isActive(Boolean.TRUE).skus(allById)
                .build();
        comboPromotionConfig.setType(PromotionConfig.Type.COMBO_PROMO);
        comboPromotionConfig.setName("C_D_COMBO");
        comboPromotionRepository.save(comboPromotionConfig);
        //Setting Up N-th Promotions
        //For A
        StockKeepingUnit A = skuRepository.findById(1l).get();
        NItemsPromotionConfig aItemsPromotion = NItemsPromotionConfig.builder().fixedPrice(BigDecimal.valueOf(130l))
                .id(1l).isActive(Boolean.TRUE).skus(A).quantity(3l).build();
        aItemsPromotion.setType(PromotionConfig.Type.N_ITEMS_PROMO);
        aItemsPromotion.setName("PACK_OF_3");
        nItemsPromotionRepository.save(aItemsPromotion);
        //For B
        StockKeepingUnit B = skuRepository.findById(2l).get();
        NItemsPromotionConfig bItemsPromotion = NItemsPromotionConfig.builder().fixedPrice(BigDecimal.valueOf(45l))
                .id(2l).isActive(Boolean.TRUE).skus(B).quantity(2l).build();
        bItemsPromotion.setType(PromotionConfig.Type.N_ITEMS_PROMO);
        bItemsPromotion.setName("PACK_OF_2");
        nItemsPromotionRepository.save(bItemsPromotion);
    }

}
