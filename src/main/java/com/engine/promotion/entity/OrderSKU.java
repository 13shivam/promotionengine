package com.engine.promotion.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSKU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    private StockKeepingUnit sku;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "quantity")
    private Long quantity;

}
