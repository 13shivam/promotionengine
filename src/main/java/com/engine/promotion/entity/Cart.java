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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "discountApplied")
    private Boolean discountApplied;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderSKU> orderSkus;

}
