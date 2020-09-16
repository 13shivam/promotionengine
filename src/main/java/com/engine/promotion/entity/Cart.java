package com.engine.promotion.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "discountApplied")
    private Boolean discountApplied;

    //@JoinColumn(name = "sku_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderSKU> orderSkus;

}
