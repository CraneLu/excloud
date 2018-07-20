package com.lph.excloudapiprice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("price")
public class PriceController {


    @GetMapping("token")
    public String test(String token){
        return token;
    }
}
