package com.engine.promotion.controllers;

import com.engine.promotion.constants.RestUrls;
import com.engine.promotion.dto.request.RequestOrderSKUs;
import com.engine.promotion.dto.response.ResponseOrderSKUs;
import com.engine.promotion.promotionengine.ApplyPromotion;
import com.engine.promotion.promotionengine.CartPromotionsBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestUrls.API_V1)
public class PromotionsController {

    @Autowired
    private ApplyPromotion applyPromotion;

    @RequestMapping(method = RequestMethod.POST, path = "/promotion/apply")
    public ResponseEntity<ResponseOrderSKUs> applyPromotion(@RequestBody RequestOrderSKUs requestOrderSKUs) {
        CartPromotionsBO promotionBO = applyPromotion.createPromotionBO(requestOrderSKUs);
        ResponseOrderSKUs responseOrderSKUses = applyPromotion.applyPromotions(promotionBO);
        return new ResponseEntity<>(responseOrderSKUses, HttpStatus.OK);
    }

}
