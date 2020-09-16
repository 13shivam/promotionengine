package com.engine.promotion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
public class SKUPriceDto {

    @JsonProperty("sku_id")
    private Set<Long> skuId;

    @JsonProperty("name")
    private Set<String> name;

    @JsonProperty("is_combo_offer")
    private Boolean isComboOffer;

    @JsonProperty("fix_pack_offer")
    private Boolean fixPackOffer;

    @JsonProperty("quantity")
    private Long quantity;

    @JsonProperty("amount")
    private BigDecimal amount;
}
