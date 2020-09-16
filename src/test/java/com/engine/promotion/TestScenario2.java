package com.engine.promotion;

import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.entity.ComboPromotionConfig;
import com.engine.promotion.entity.NItemsPromotionConfig;
import com.engine.promotion.entity.PromotionConfig;
import com.engine.promotion.entity.StockKeepingUnit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestScenario2 {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static String testScenario2 = "{\n" +
            "  \"cart_id\" : 1,\n" +
            "  \"order_details\" : [ {\n" +
            "    \"sku_id\" : 1,\n" +
            "    \"quantity\" : 5\n" +
            "  }, {\n" +
            "    \"sku_id\" : 2,\n" +
            "    \"quantity\" : 5\n" +
            "  }, {\n" +
            "    \"sku_id\" : 3,\n" +
            "    \"quantity\" : 1\n" +
            "  } ]\n" +
            "}";

    private static String promotionConfigJson2 = "{\n" +
            "  \"name\" : \"C_D_COMBO\",\n" +
            "  \"type\" : \"COMBO_PROMO\",\n" +
            "  \"id\" : 1,\n" +
            "  \"isActive\" : true,\n" +
            "  \"combineFixedPrice\" : 3E+1,\n" +
            "  \"skus\" : [ {\n" +
            "    \"id\" : 3,\n" +
            "    \"name\" : \"C\",\n" +
            "    \"unitPrice\" : 2E+1,\n" +
            "    \"quantity\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 4,\n" +
            "    \"name\" : \"D\",\n" +
            "    \"unitPrice\" : 15,\n" +
            "    \"quantity\" : 10\n" +
            "  } ]\n" +
            "}";

    private static String NItemConfig = "[ {\n" +
            "  \"name\" : \"PACK_OF_3\",\n" +
            "  \"type\" : \"N_ITEMS_PROMO\",\n" +
            "  \"id\" : 1,\n" +
            "  \"quantity\" : 3,\n" +
            "  \"fixedPrice\" : 1.3E+2,\n" +
            "  \"isActive\" : true,\n" +
            "  \"skus\" : {\n" +
            "    \"id\" : 1,\n" +
            "    \"name\" : \"A\",\n" +
            "    \"unitPrice\" : 5E+1,\n" +
            "    \"quantity\" : 10\n" +
            "  }\n" +
            "}, {\n" +
            "  \"name\" : \"PACK_OF_2\",\n" +
            "  \"type\" : \"N_ITEMS_PROMO\",\n" +
            "  \"id\" : 2,\n" +
            "  \"quantity\" : 2,\n" +
            "  \"fixedPrice\" : 45,\n" +
            "  \"isActive\" : true,\n" +
            "  \"skus\" : {\n" +
            "    \"id\" : 2,\n" +
            "    \"name\" : \"B\",\n" +
            "    \"unitPrice\" : 3E+1,\n" +
            "    \"quantity\" : 10\n" +
            "  }\n" +
            "} ]";

    private static String skuRepoResponse = "[ {\n" +
            "  \"id\" : 1,\n" +
            "  \"name\" : \"A\",\n" +
            "  \"unitPrice\" : 5E+1,\n" +
            "  \"quantity\" : 10\n" +
            "}, {\n" +
            "  \"id\" : 2,\n" +
            "  \"name\" : \"B\",\n" +
            "  \"unitPrice\" : 3E+1,\n" +
            "  \"quantity\" : 10\n" +
            "}, {\n" +
            "  \"id\" : 3,\n" +
            "  \"name\" : \"C\",\n" +
            "  \"unitPrice\" : 2E+1,\n" +
            "  \"quantity\" : 10\n" +
            "} ]";

    public static List<StockKeepingUnit> skuRepoResponse() throws JsonProcessingException {
        return objectMapper.readValue(skuRepoResponse, new TypeReference<List<StockKeepingUnit>>() {
        });
    }

    public static List<PromotionConfig> getNthConfigScenario2() throws JsonProcessingException {
        List<NItemsPromotionConfig> nItemsPromotionConfigs = objectMapper.readValue(NItemConfig, new TypeReference<List<NItemsPromotionConfig>>() {
        });
        return nItemsPromotionConfigs.stream().map(x -> (PromotionConfig) x).collect(Collectors.toList());
    }

    public static List<PromotionConfig> getPromotionConfigScenario2() throws JsonProcessingException {
        return Arrays.asList((PromotionConfig) objectMapper.readValue(promotionConfigJson2, ComboPromotionConfig.class));
    }

    public static RequestOrderSKUs getRequestScenario2() throws JsonProcessingException {
        RequestOrderSKUs requestOrderSKUs = objectMapper.readValue(testScenario2, RequestOrderSKUs.class);
        return requestOrderSKUs;
    }
}
