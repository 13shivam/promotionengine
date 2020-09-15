package com.engine.promotion.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "n_items_promotions")
@Getter
@Setter
@Builder
public class NItemsPromotionConfig extends PromotionConfig {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "discount_factor")
    private BigDecimal discountFactor;

    @JoinColumn(name = "sku_id")
    @OneToOne(fetch = FetchType.EAGER)
    private StockKeepingUnit skus;


}
