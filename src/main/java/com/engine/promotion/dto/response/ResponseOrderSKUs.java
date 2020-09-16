package com.engine.promotion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseOrderSKUs {

    @JsonProperty("discount_items")
    private List<SKUPriceDto> orderSKUQuantity = new ArrayList<SKUPriceDto>();

    @JsonProperty("net_amount")
    private BigDecimal netAmount;

}
