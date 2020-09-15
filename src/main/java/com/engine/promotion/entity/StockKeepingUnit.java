package com.engine.promotion.entity;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "skus")
@Getter
@Setter
@Builder
public class StockKeepingUnit {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sku", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<StockKeepingUnitPrice> skuPrices = Lists.newArrayList();

}
