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

    /*
  Maintains Combo-Items Promotion Configuration
   */

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @JoinColumn(name = "combine_fixed_price")
    private BigDecimal combineFixedPrice;

    @OneToMany(fetch = FetchType.EAGER)
    private List<StockKeepingUnit> skus;

}
