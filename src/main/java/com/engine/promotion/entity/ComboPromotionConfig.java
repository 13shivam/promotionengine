package com.engine.promotion.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "combo_promotions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComboPromotionConfig extends PromotionConfig {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 /*   @Column(name = "sku_id", insertable = false, updatable = false)
    private Long skuId;*/


    @Column(name = "is_active")
    private Boolean isActive;

    @JoinColumn(name = "combine_fixed_price")
    private BigDecimal combineFixedPrice;

    @OneToMany(fetch = FetchType.LAZY)
   // @JoinColumn(name = "sku_id")
    private List<StockKeepingUnit> skus;

}
