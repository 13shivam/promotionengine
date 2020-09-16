package com.engine.promotion.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestOrderSKUs {

    @JsonProperty("cart_id")
    private Long cartId;

    @JsonProperty("order_details")
    private List<OrderSKUQuantity> orderSKUQuantity;
}
