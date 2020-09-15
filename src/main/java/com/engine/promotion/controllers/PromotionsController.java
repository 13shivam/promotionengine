package com.engine.promotion.controllers;

import com.engine.promotion.constants.RestUrls;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestUrls.API_V1)
public class PromotionsController {

    @RequestMapping(method = RequestMethod.GET, path = "/promotion/apply")
    public ResponseEntity<Object> applyPromotion() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
