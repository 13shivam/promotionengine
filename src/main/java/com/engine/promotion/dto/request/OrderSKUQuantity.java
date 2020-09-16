package com.engine.promotion.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSKUQuantity {

    @JsonProperty("sku_id")
    private Long skuId;

    @JsonProperty("quantity")
    private Long quantity;
}
