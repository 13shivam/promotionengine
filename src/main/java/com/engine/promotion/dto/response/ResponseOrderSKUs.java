package com.engine.promotion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseOrderSKUs {

    @JsonProperty("discount_items")
    private List<SKUPriceDto> orderSKUQuantity;
}
