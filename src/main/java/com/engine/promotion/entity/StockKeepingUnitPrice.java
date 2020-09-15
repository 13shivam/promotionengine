package com.engine.promotion.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "sku_prices")
@Builder
public class StockKeepingUnitPrice {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", nullable = false, unique = true)
    private StockKeepingUnit sku;

    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

}
