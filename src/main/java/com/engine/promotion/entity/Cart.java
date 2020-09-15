package com.engine.promotion.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@Builder
public class Cart {

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
