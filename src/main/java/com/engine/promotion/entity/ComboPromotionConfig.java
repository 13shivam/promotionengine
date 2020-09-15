package com.engine.promotion.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "combo_promotions")
@Getter
@Setter
@Builder
public class ComboPromotionConfig extends PromotionConfig {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "sku_id")
    private Long skuId;

    @JoinColumn(name = "sku_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<StockKeepingUnit> skus;


}
