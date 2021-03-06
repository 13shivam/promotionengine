package com.engine.promotion.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "n_items_promotions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NItemsPromotionConfig extends PromotionConfig {

    /*
    Maintains N-Items Promotion Configuration
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "fixed_price")
    private BigDecimal fixedPrice;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    private StockKeepingUnit skus;

}
