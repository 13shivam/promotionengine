package com.engine.promotion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountSKUQuantity {

    @JsonProperty("sku_id")
    private Long skuId;

    @JsonProperty("quantity")
    private Long quantity;

    @JsonProperty("amount")
    private Long amount;
}
