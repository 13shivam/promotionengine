package com.engine.promotion.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@Builder
@MappedSuperclass
public class PromotionConfig {

    public enum Type {
        N_ITEMS_PROMO, COMBO_PROMO;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(value = STRING)
    private Type type;


}
